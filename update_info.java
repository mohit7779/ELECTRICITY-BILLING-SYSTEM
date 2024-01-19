package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class update_info extends JFrame implements ActionListener{

    JTextField addressText,cityText,stateText,emailText,phoneText,nameText,meterText;
    JButton update , cancel;
    String meter;

    update_info(String meter){
        super("Update Information");
        this.meter = meter;



        setSize(700,500);
        getContentPane().setBackground(Color.CYAN);
        setLocation(300,120);
        setLayout(null);

        JLabel heading = new JLabel("Update Information");
        heading.setBounds(50,10,400,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);

        JLabel name = new JLabel("Name");
        name.setBounds(30,70,100,20);
        add(name);

         nameText = new JTextField("");
        nameText.setBounds(150,70,200,20);
         add(nameText);

         JLabel meterno = new JLabel("Meter Number");
         meterno.setBounds(30,110,100,20);
         add(meterno);

         meterText = new JTextField("");
        meterText.setBounds(150,110,100,20);
         add(meterText);

         JLabel address = new JLabel("Address");
         address.setBounds(30,150,100,20);
         add(address);

         addressText = new JTextField("");
         addressText.setBounds(150,150,200,20);
         add(addressText);

         JLabel city = new JLabel("City");
         city.setBounds(30,190,100,20);
         add(city);

         cityText = new JTextField("");
         cityText.setBounds(150,190,200,20);
         add(cityText);

         JLabel state = new JLabel("State");
         state.setBounds(30,230,100,20);
         add(state);

        stateText = new JTextField("");
        stateText.setBounds(150,230,200,20);
         add(stateText);

         JLabel email = new JLabel("Email");
         email.setBounds(30,270,100,20);
         add(email);

        emailText = new JTextField("");
        emailText.setBounds(150,270,200,20);
         add(emailText);

         JLabel phone = new JLabel("Phone");
         phone.setBounds(30,310,100,20);
         add(phone);

         phoneText = new JTextField("");
        phoneText.setBounds(150,310,200,20);
         add(phoneText);


         try{
             database c = new database();
             ResultSet resultSet = c.statement.executeQuery("select * from new_Customer where meterno = '"+meter+"'");
             if(resultSet.next()){
                 nameText.setText(resultSet.getString("name"));
                 meterText.setText(resultSet.getString("meterno"));
                 addressText.setText(resultSet.getString("address"));
                 cityText.setText(resultSet.getString("city"));
                 stateText.setText(resultSet.getString("state"));
                 emailText.setText(resultSet.getString("email"));
                 phoneText.setText(resultSet.getString("phone_no"));
             }
         }catch (Exception E){
             E.printStackTrace();
         }


         update = new JButton("Update");
         update.setBounds(50,360,120,25);
         update.setForeground(Color.WHITE);
         update.setBackground(Color.ORANGE);
         update.addActionListener(this);
         add(update);

         cancel = new JButton("Cancel");
         cancel.setBackground(Color.ORANGE);
         cancel.setForeground(Color.WHITE);
         cancel.addActionListener(this);
         cancel.setBounds(200,360,120,25);
         add(cancel);


         ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/update.png"));
         Image image = imageIcon.getImage().getScaledInstance(400,410,Image.SCALE_DEFAULT);
         ImageIcon imageIcon1 = new ImageIcon(image);
         JLabel imageLabel = new JLabel(imageIcon1);
         imageLabel.setBounds(360,0,400,410);
         add(imageLabel);

        setVisible(true);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == update){
            String saddress = addressText.getText();
            String scity = cityText.getText();
            String sstate = stateText.getText();
            String semail = emailText.getText();
            String sphone = phoneText.getText();

            try{
                database c = new database();
                c.statement.executeUpdate("update new_Customer set address = '"+saddress+"', city = ,'"+scity+"', state = '"+sstate+"',email = '"+semail+"', phone_no = '"+sphone+"' where meterno = '"+meter+"'");

                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
                setVisible(true);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }

    }

    public static void main(String[] args) {
        new update_info("");
    }
}
