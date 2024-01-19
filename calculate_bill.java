package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;
import java.util.jar.JarFile;

public class calculate_bill extends JFrame implements ActionListener {

    JLabel nameText,addressText;
    TextField unitText;
    Choice meternochoise,monthChoise;
    JButton submit,cancel;

    calculate_bill(){

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        add(panel);

        JLabel heading = new JLabel("calculate Electricity Bill");
        heading.setBounds(70,10,300,20);
        heading.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(heading);

        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(50,80,100,20);
        panel.add(meterno);

        meternochoise = new Choice();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_Customer");
            while(resultSet.next()){
                meternochoise.add(resultSet.getString("meterno"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }
            meternochoise.setBounds(180,80,100,20);
            panel.add(meternochoise);

//
            JLabel name = new JLabel("Name");
            name.setBounds(50,120,100,20);
            panel.add(name);

            nameText = new JLabel("");
            nameText.setBounds(180,120,150,20);
            panel.add(nameText);

            JLabel address = new JLabel("Address");
            address.setBounds(50,160,100,20);
            panel.add(address);

            addressText = new JLabel("");
            addressText.setBounds(180,160,150,20);
            panel.add(addressText);

            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery("select * from new_Customer where meterno = '"+meternochoise.getSelectedItem()+"' ");
                while(resultSet.next()){
                    nameText.setText(resultSet.getString("name"));
                    addressText.setText(resultSet.getString("address"));
                }
            }catch (Exception E){
                E.printStackTrace();
            }

            JLabel unitconsumed = new JLabel("Unit Consumed");
            unitconsumed.setBounds(50,200,100,20);
            panel.add(unitconsumed);

            unitText = new TextField();
            unitText.setBounds(180,200,150,20);;
            panel.add(unitText);

            JLabel month = new JLabel("Month");
            month.setBounds(50,240,100,20);
            panel.add(month);

        monthChoise = new Choice();
        monthChoise.add("january");
        monthChoise.add("feburary");
        monthChoise.add("march");
        monthChoise.add("april");
        monthChoise.add("may");
        monthChoise.add("june");
        monthChoise.add("july");
        monthChoise.add("august");
        monthChoise.add("september");
        monthChoise.add("october");
        monthChoise.add("november");
        monthChoise.add("december");
        monthChoise.setBounds(180,240,150,20);
        panel.add(monthChoise);


        submit = new JButton("Submit");
        submit.setBounds(80,300,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);

        cancel = new JButton("Cancel");
        cancel.setBounds(220,300,100,25);
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.addActionListener(this);
        panel.add(cancel);


        setLayout(new BorderLayout());
        add(panel,"Center");
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/budget.png"));
        Image image = imageIcon.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel imageLabel = new JLabel(imageIcon1);
        add(imageLabel,"East");



        setSize(650,400);
        setLocation(400,150);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource() == submit){
            String smeterno = meternochoise.getSelectedItem();
            String sunit = unitText.getSelectedText();
            String smonth = monthChoise.getSelectedItem();

            int totalBill = 0;
            int units = Integer.parseInt(sunit);
            String query_tax = "select * from tax";

            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_tax);
                while(resultSet.next()){
                    totalBill += units * Integer.parseInt(resultSet.getString("cost_per_unti"));
                    totalBill += Integer.parseInt(resultSet.getString("meter_rent"));
                    totalBill += Integer.parseInt(resultSet.getString("service_charge"));
                    totalBill += Integer.parseInt(resultSet.getString("swacch_bharat"));
                    totalBill += Integer.parseInt(resultSet.getString("fixed_tax"));
                }
            }catch (Exception E){
                E.printStackTrace();
            }
            String query_total_bill = "insert into bill values('"+smeterno+"','"+smonth+"','"+sunit+"','"+totalBill+"','Not Paid')";

            try{
                database c = new database();
                ResultSet resultSet = c.statement.executeQuery(query_total_bill);

                JOptionPane.showMessageDialog(null,"Customer bill updated successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }


        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new calculate_bill();
    }
}
