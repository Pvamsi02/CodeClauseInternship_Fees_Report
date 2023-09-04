import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.sql.*;

public class GetStudent extends JFrame  implements ActionListener {
//    Jthis this;
    JTextField regno;
    JLabel label1;
    JButton submit,printbtn,modify;
    JTextArea area;

    Font font = new Font("SANS_SERIF", Font.ITALIC, 15);
    Font font1 = new Font("SANS_SERIF", Font.ITALIC, 20);
    GetStudent(){
        ImageIcon icon1=new ImageIcon("src\\iconMain.png","");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Student info Page");
        this.setSize(400,150);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(202, 230, 239));

        label1 = new JLabel("Register No:");
        label1.setFont(font);
        label1.setBounds(5, 10, 150, 30);
        regno=new JTextField();
        regno.setFont(font);
        regno.setBounds(150, 10, 200, 30);

        submit=new JButton("PRINT");
        submit.setBounds(100, 50, 100, 50);
        submit.setFocusable(false);
        submit.setBackground(new Color(240, 74, 91));
        submit.setBorderPainted(false);
        submit.addActionListener(this);
        submit.setFont(font);
        
        modify=new JButton("MODIFY");
        modify.setBounds(220, 50, 100, 50);
        modify.setFocusable(false);
        modify.setBackground(new Color(240, 74, 91));
        modify.setBorderPainted(false);
        modify.addActionListener(this);
        modify.setFont(font);
        
        area=new JTextArea("*****************************************************************************************" +
                         "\n*********************************  Fees Report PV   *************************************" +
                         "\n*****************************************************************************************");
        area.setBounds(10,50,470,400);
        area.setFont(font);
        printbtn=new JButton("PRINT");
        printbtn.setBounds(100, 460, 200, 40);
        printbtn.setFocusable(false);
        printbtn.setBackground(new Color(240, 74, 91));
        printbtn.setBorderPainted(false);
        printbtn.addActionListener(this);
        printbtn.setFont(font);

    
        this.add(label1);
        this.add(regno);
        this.add(submit);
        this.add(modify);
        this.setVisible(true);
        this.setLocationRelativeTo(null);



    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==submit)
        {
            String urlString = "jdbc:mysql://localhost:3306/feesreport";
            String uName = "root";
            String uPassword = "CPVamsi@0212";
            String reg=regno.getText();
            String query="select * from student where regno='"+reg+"'";
            System.out.println(query);
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(urlString, uName, uPassword);
                Statement st= con.createStatement();
                ResultSet rs=st.executeQuery(query);
                rs.next();
                String stname,streg,stparent;
                int stmobile,stfees;
                stname=rs.getString(1);
                streg=rs.getString(2);
                stparent=rs.getString(3);
                stmobile=rs.getInt(4);
                stfees=rs.getInt(5);

                area.append("\nName  \t\t"+stname);
                area.append("\nRegister Number: \t"+streg);
                area.append("\nParent Name:     \t"+stparent);
                area.append("\nMobile Number:   \t"+stmobile);
                area.append("\nFees Pending:    \t"+stfees);
                st.close();
                con.close();

            } catch (Exception ex) {
                System.out.println(ex);
            }

            this.add(area);
            this.remove(submit);
            this.remove(modify);
            this.add(printbtn);
            this.setSize(500,550);
            this.setLocationRelativeTo(null);

            regno.setEditable(false);
            regno.setFocusable(false);
            area.setEditable(false);
            area.setFocusable(false);
        }

        if (e.getSource()==modify)
        {
            dispose();
            new ModifyStudent(regno.getText());
        }

        if(e.getSource()==printbtn)
        {
            try {
                area.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }
            dispose();
        }
    }

}
