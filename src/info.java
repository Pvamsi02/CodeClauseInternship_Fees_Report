import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class info extends JFrame implements ActionListener
{
    JButton addStudent,getStudent;
    JLabel title;
    Font font=new Font("SANS_SERIF",Font.ITALIC,15);
    info(int uid){
        ImageIcon icon1=new ImageIcon("src\\iconMain.png","");
        this.setIconImage(icon1.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Info Page");
        this.setSize(280,200);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(202, 230, 239));

        title=new JLabel("Welcome "+uid+" Choose these");
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setBounds(0,0,280,30);
        title.setFont(font);

        addStudent=new JButton("Add Student");
        addStudent.setBounds(50,40,150,50);
        addStudent.setFocusable(false);
        addStudent.setBackground(new Color(240, 74, 91));
        addStudent.setBorderPainted(false);
        addStudent.addActionListener(this);
        addStudent.setFont(font);
        
        getStudent=new JButton("Get Student");
        getStudent.setBounds(50,110,150,50);
        getStudent.setFocusable(false);
        getStudent.setBackground(new Color(240, 74, 91));
        getStudent.setBorderPainted(false);
        getStudent.addActionListener(this);
        getStudent.setFont(font);

        this.add(title);
        this.add(addStudent);
        this.add(getStudent);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==addStudent)
        {
            dispose();
            new StudentAdd();
        }
        else if (e.getSource()==getStudent) {
            dispose();
            new GetStudent();
        }
    }
}
