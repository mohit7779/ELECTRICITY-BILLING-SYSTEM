package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class view_information extends JFrame implements ActionListener {

    JButton cancel;
    String view;
    view_information(String view){

        this.view = view;

        JLabel heading = new JLabel("view Customer information");
        heading.setBounds(230,0,500,40);
        heading.setFont(new Font("serif",Font.BOLD,20));
        add(heading);


        JLabel nameLbael = new JLabel("Name");
        nameLbael.setBounds(70,80,100,20);
        add(nameLbael);

        JLabel nameLabelText = new JLabel("");
        nameLabelText.setBounds(200,80,150,80);
        add(nameLabelText);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(70,140,100,20);
        add(meterno);

        JLabel meternoText = new JLabel("");
        meternoText.setBounds(200,140,150,20);
        add(meternoText);

        JLabel address = new JLabel("Address");
        address.setBounds(70,200,100,20);
        add(address);

        JLabel addressText = new JLabel("");
        addressText.setBounds(200,200,150,20);
        add(addressText);

        JLabel city = new JLabel("City");
        city.setBounds(70,260,100,20);
        add(city);

        JLabel cityText = new JLabel("");
        cityText.setBounds(200,260,150,20);
        add(cityText);

        JLabel state = new JLabel("State");
        state.setBounds(500,80,100,20);
        add(state);

        JLabel stateText = new JLabel("");
        stateText.setBounds(600,80,150,20);
        add(stateText);

        JLabel email = new JLabel("Email");
        email.setBounds(500,140,100,20);
        add(email);

        JLabel emailText = new JLabel("");
        emailText.setBounds(600,140,150,20);
        add(emailText);

        JLabel phone = new JLabel("Phone");
        phone.setBounds(500,200,100,20);
        add(phone);

        JLabel phoneText = new JLabel("");
        phoneText.setBounds(600,200,150,20);
        add(phoneText);


        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_Customer where meterno = '"+view+"'");
            if(resultSet.next()){
                nameLabelText.setText(resultSet.getString("name"));
                meternoText.setText(resultSet.getString("meterno"));
                addressText.setText(resultSet.getString("address"));
                cityText.setText(resultSet.getString("city"));
                stateText.setText(resultSet.getString("state"));
                emailText.setText(resultSet.getString("email"));
                phoneText.setText(resultSet.getString("phone_no"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }

        cancel = new JButton("Cancel");
        cancel.setBackground(Color.ORANGE);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(220,350,120,25);
        cancel.addActionListener(this);
        add(cancel);

        ImageIcon a1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewInfo.png"));
        Image a2 = a1.getImage().getScaledInstance(600,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(a2);
        JLabel img = new JLabel(i3);
        img.setBounds(100,320,600,300);
        add(img);



        setSize(900,700);
        setLocation(300,20);
        getContentPane().setBackground(Color.CYAN);
        setLayout(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancel){
            setVisible(false);
            new Signup();
        }

    }

    public static void main(String[] args) {

        new view_information("");
    }
}
