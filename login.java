package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.jar.JarFile;

public class login extends JFrame implements ActionListener {

    JTextField userText,passwordText;
    Choice loginChoice;

    JButton loginButton,cancelBUtton,signupButton;
    login(){
        super("Login");  // heading
        getContentPane().setBackground(Color.CYAN);
        JLabel username = new JLabel("UserName");
        username.setBounds(300,60,100,20);
        add(username);

        userText = new JTextField();
        userText.setBounds(400,60,150,20);
        add(userText);



        JLabel password = new JLabel("password");
        password.setBounds(300,100,100,20);
        add(password);

        passwordText = new JTextField();
        passwordText.setBounds(400,100,150,20);
        add(passwordText);

        JLabel login = new JLabel("login in As");
        login.setBounds(300,140,100,20);
        add(login);

        loginChoice = new Choice();
        loginChoice.add("Admin");
        loginChoice.add("Customer");
        loginChoice.setBounds(400,140,150,20);
        add(loginChoice);

        //button
        loginButton = new JButton("Login");
        loginButton.setBounds(330,180,100,20);
        loginButton.addActionListener(this);
        add(loginButton);

        cancelBUtton = new JButton("cancel");
        cancelBUtton.setBounds(460,180,100,20);
        cancelBUtton.addActionListener(this);
        add(cancelBUtton);

        signupButton = new JButton("signup");
        signupButton.setBounds(400,215,100,20);
        signupButton.addActionListener(this);
        add(signupButton);

        ImageIcon profileOne = new ImageIcon(ClassLoader.getSystemResource("icon/profile2.jpg"));
        Image profileTwo = profileOne.getImage().getScaledInstance(250,250,Image.SCALE_DEFAULT);
        ImageIcon finaleprofileOne =  new ImageIcon(profileTwo);
        JLabel profileLable = new JLabel(profileOne);
        profileLable.setBounds(0,0,250,250);
        add(profileLable);




        setSize(640,300);
        setLocation(400,200);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginButton){
            String username = userText.getText();
            String spassword = passwordText.getText();
            String suser = loginChoice.getSelectedItem();

            try{
                database c = new database();
                String query = "select * from Signup where username = '"+username+"' and password = '"+spassword+"'" +
                        " and usertype = '"+suser+"'";
                ResultSet resultSet = c.statement.executeQuery(query);

                if(resultSet.next()){
                    String meter = resultSet.getString("meter_no");
                    setVisible(false);
                    new main_class(suser,meter);
                }else {
                    JOptionPane.showMessageDialog(null,"Invalid Login");
                }

            }catch (Exception E){
                E.printStackTrace();
            }

        } else if (e.getSource()== cancelBUtton) {
            setVisible(false);
        } else if (e.getSource() == signupButton) {
            setVisible(false);
            new Signup();
        }
    }

    public static void main(String[] args) {

        new login();
    }
}
