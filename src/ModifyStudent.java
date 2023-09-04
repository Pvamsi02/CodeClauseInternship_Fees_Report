import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class ModifyStudent extends JFrame implements ActionListener {
    JButton modify;
    JTextField name, regNo, parentName, mobileNo, fees;
    JLabel label1, label2, label3, label4, label5;
    Font font = new Font("SANS_SERIF", Font.ITALIC, 15);

    ModifyStudent(String reg) {
        ImageIcon icon1 = new ImageIcon("src\\iconMain.png", "");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Add Student");
        this.setSize(400, 350);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(202, 230, 239));

        label1 = new JLabel("Name:");
        label1.setFont(font);
        label1.setBounds(5, 10, 150, 30);
        label2 = new JLabel("Register Number:");
        label2.setFont(font);
        label2.setBounds(5, 50, 150, 30);
        label3 = new JLabel("Parent Name:");
        label3.setFont(font);
        label3.setBounds(5, 90, 150, 30);
        label4 = new JLabel("Mobile Number:");
        label4.setFont(font);
        label4.setBounds(5, 130, 150, 30);
        label5 = new JLabel("Fees Due:");
        label5.setFont(font);
        label5.setBounds(5, 180, 150, 30);


        String urlString = "jdbc:mysql://localhost:3306/feesreport";
        String uName = "root";
        String uPassword = "CPVamsi@0212";
        String query = "select * from student where regno='" + reg + "'";
        System.out.println(query);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(urlString, uName, uPassword);
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(query);
            rs.next();
            String stname, streg, stparent;
            int stmobile, stfees;
            stname = rs.getString(1);
            streg = rs.getString(2);
            stparent = rs.getString(3);
            stmobile = rs.getInt(4);
            stfees = rs.getInt(5);
            name = new JTextField(stname);
            regNo = new JTextField(streg);
            parentName = new JTextField(stparent);
            mobileNo = new JTextField(String.valueOf(stmobile));
            fees = new JTextField(String.valueOf(stfees));
            query = "delete from student where regno='" + reg + "'";
            st.close();
            Statement st1 = con.createStatement();
            int count = st1.executeUpdate(query);
            st1.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }


        name.setFont(font);
        name.setBounds(150, 10, 200, 30);
        regNo.setFont(font);
        regNo.setBounds(150, 50, 200, 30);
        parentName.setFont(font);
        parentName.setBounds(150, 90, 200, 30);
        mobileNo.setFont(font);
        mobileNo.setBounds(150, 130, 200, 30);
        fees.setFont(font);
        fees.setBounds(150, 180, 200, 30);

        modify = new JButton("MODIFY");
        modify.setBounds(100, 250, 200, 50);
        modify.setFocusable(false);
        modify.setBackground(new Color(240, 74, 91));
        modify.setBorderPainted(false);
        modify.addActionListener(this);
        modify.setFont(font);


        this.add(label1);
        this.add(label2);
        this.add(label3);
        this.add(label4);
        this.add(label5);

        this.add(name);
        this.add(regNo);
        this.add(parentName);
        this.add(mobileNo);
        this.add(fees);

        this.add(modify);
        this.setVisible(true);
        this.setLocationRelativeTo(null);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == modify) {
            String stname = name.getText();
            String streg = regNo.getText();
            String stparent = parentName.getText();
            String stmobile = mobileNo.getText();
            String stfees = fees.getText();
            if (stname.isEmpty() || stparent.isEmpty() || streg.isEmpty() || stfees.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Empty Field Found", "Error", JOptionPane.WARNING_MESSAGE);
            }
            if (stmobile.length() < 10) {
                JOptionPane.showMessageDialog(null, "Wrong Mobile Number", "Error", JOptionPane.WARNING_MESSAGE);
            }
            String urlString = "jdbc:mysql://localhost:3306/feesreport";
            String uName = "root";
            String uPassword = "CPVamsi@0212";
            String query = "insert into student values (?,?,?,?,?)";
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(urlString, uName, uPassword);
                PreparedStatement pst = con.prepareStatement(query);
                pst.setString(1, stname);
                pst.setString(2, streg);
                pst.setString(3, stparent);
                pst.setInt(4, Integer.parseInt(stmobile));
                pst.setInt(5, Integer.parseInt(stfees));
                System.out.println(pst.executeUpdate());
                JOptionPane.showMessageDialog(null, "Modified", "confirm", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                pst.close();
                con.close();
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }

    }
}
