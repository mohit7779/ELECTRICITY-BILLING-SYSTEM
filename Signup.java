package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;


public class Signup extends JFrame implements ActionListener {

    Choice LoginAsCho;
    TextField meterText,EmployerText,UserNameText,nameText,passwordText;
    JButton create,Back;
    Signup(){


        super("Signup page");  // for heading


        getContentPane().setBackground(Color.CYAN); //bg color

        JLabel createAs = new JLabel("Create Account As");
        createAs.setBounds(30,50,125,20);
        add(createAs);

        LoginAsCho = new Choice();
        LoginAsCho.add("Admin");
        LoginAsCho.add("Customer");
        LoginAsCho.setBounds(170,50,120,20);
        add(LoginAsCho);


        JLabel meterNo = new JLabel("Meter Number");
        meterNo.setBounds(30,100,120,20);
        meterNo.setVisible(false);
        add(meterNo);


        meterText = new TextField();
        meterText.setBounds(170,100,125,20);
        meterText.setVisible(false);
        add(meterText);


        JLabel Employer = new JLabel("Employer ID");
        Employer.setBounds(30,100,125,20);
        Employer.setVisible(true);
        add(Employer);


        EmployerText = new TextField();
        EmployerText.setBounds(170,100,125,20);
        EmployerText.setVisible(true);
        add(EmployerText);


        JLabel userName = new JLabel("UserName");
        userName.setBounds(30,140,125,20);
        add(userName);

        UserNameText = new TextField();
        UserNameText.setBounds(170,140,125,20);
        add(UserNameText);



        JLabel name = new JLabel("Name");
        name.setBounds(30,180,125,20);
        add(name);

        nameText = new TextField("");
        nameText.setBounds(170,180,125,20);
        add(nameText);

        meterText.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                try{
                    database c = new database();
                    ResultSet resultSet = c.statement.executeQuery("select * from Signup where meter_no = '"+meterText.getText()+"'");
                    if(resultSet.next()){
                        nameText.setText(resultSet.getString("name"));
                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        JLabel password = new JLabel("password");
        password.setBounds(30,220,125,20);
        add(password);

        passwordText = new TextField();
        passwordText.setBounds(170,220,125,20);
        add(passwordText);

        LoginAsCho.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                String user = LoginAsCho.getSelectedItem();
                if(user.equals("Customer")){
                    Employer.setVisible(false);
                    EmployerText.setVisible(false);
                    nameText.setEditable(false);
                    meterNo.setVisible(true);
                    meterText.setVisible(true);
                }else{
                    Employer.setVisible(true);
                    EmployerText.setVisible(true);
                    meterNo.setVisible(false);
                    meterText.setVisible(false);
                }
            }
        });

        //buttons:
        create = new JButton("Create");
        create.setBackground(Color.ORANGE); // button color
        create.setForeground(Color.BLACK); // text color
        create.setBounds(50,280,100,25);
        create.addActionListener(this); // to perform action
        add(create);

        Back = new JButton("Back");
        Back.setBackground(Color.ORANGE);
        Back.setForeground(Color.BLACK);
        Back.setBounds(180,280,100,25);
        Back.addActionListener(this);
        add(Back);


        ImageIcon boyIcon = new ImageIcon(ClassLoader.getSystemResource("icon/boyIcon.jpg"));
        Image boyImg = boyIcon.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon boyIcon2 = new ImageIcon(boyImg);
        JLabel boyLabel = new JLabel(boyIcon2);
        boyLabel.setBounds(320,30,250,250);
        add(boyLabel);

        setSize(600,380);
        setLocation(500,200);
        setLayout(null);
        setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == create){
            String sloginAs = LoginAsCho.getSelectedItem();
            String susername = UserNameText.getText();
            String Sname = nameText.getText();
            String spassword = passwordText.getText();
            String smeter = meterText.getText();
            try{
                database c = new database();
                String query = null;
                if(LoginAsCho.equals("Admin")){
                }else{
                    query = "insert into Signup value('"+smeter+"','"+susername+"','"+Sname+"','"+spassword+"','"+sloginAs+"')";
                }
                query = "update Signup set username = '"+susername+"',password = '"+spassword+"',userType = '"+sloginAs+"'where meter_no = '"+smeter+"'";

                c.statement.executeUpdate(query);

                JOptionPane.showMessageDialog(null,"Account Created Successfully");
                setVisible(false);
                new login();

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource() == Back) {
            setVisible(false);
            new login();
        }
    }

    public static void main(String[] args) {
        new Signup();
    }
}
