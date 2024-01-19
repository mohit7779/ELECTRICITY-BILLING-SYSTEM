package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.ResultSet;

public class pay_bill extends JFrame implements ActionListener {

    Choice searchmonthChoise;

    JButton pay,back;
    String meter;
    pay_bill(String meter){

        this.meter = meter;
        setSize(900,600);
        setLocation(300,100);
        getContentPane().setBackground(Color.CYAN);
        setLayout(null);

        JLabel heading = new JLabel("Pay Bill");
        heading.setFont(new Font("Tahoma",Font.BOLD,24));
        heading.setBounds(120,5,400,30);
        add(heading);


        JLabel meterno = new JLabel("Meter Number");
        meterno.setBounds(35,80,200,20);
        add(meterno);

        JLabel meternoText = new JLabel("");
        meternoText.setBounds(300,80,200,20);
        add(meternoText);

        JLabel name = new JLabel("Name");
        name.setBounds(35,140,200,20);
        add(name);

        JLabel nameText = new JLabel("");
        nameText.setBounds(300,140,200,20);
        add(nameText);

        JLabel month = new JLabel("Month");
        month.setBounds(35,200,200,20);
        add(month);

        searchmonthChoise = new Choice();
        searchmonthChoise.add("january");
        searchmonthChoise.add("feburary");
        searchmonthChoise.add("march");
        searchmonthChoise.add("april");
        searchmonthChoise.add("may");
        searchmonthChoise.add("june");
        searchmonthChoise.add("july");
        searchmonthChoise.add("august");
        searchmonthChoise.add("september");
        searchmonthChoise.add("october");
        searchmonthChoise.add("november");
        searchmonthChoise.add("december");
        searchmonthChoise.setBounds(300,200,150,20);
        add(searchmonthChoise);

        JLabel unit = new JLabel("Unit");
        unit.setBounds(35,260,200,20);
        add(unit);

        JLabel unitText = new JLabel("");
        unitText.setBounds(300,140,200,20);
        add(unitText);

        JLabel totalbill = new JLabel("Total Bill");
        totalbill.setBounds(35,320,300,20);
        add(totalbill);

        JLabel totalbillText = new JLabel("");
        totalbillText.setBounds(300,320,200,20);
        add(totalbillText);


        JLabel status = new JLabel("Status");
        status.setBounds(35,380,200,20);
        add(status);

        JLabel statusText = new JLabel("");
        statusText.setBounds(300,380,200,20);
        statusText.setForeground(Color.red);
        add(statusText);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from new_Customer where meterno = '"+meter+"'");
            while(resultSet.next()){
                nameText.setText(resultSet.getString("name"));

            }
        }catch (Exception E){
            E.printStackTrace();
        }

        searchmonthChoise.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                database c = new database();
                try{
                    ResultSet resultSet = c.statement.executeQuery("select * from bill where meterno = '"+meter+"' and month = '"+searchmonthChoise.getSelectedItem()+"'");
                    while(resultSet.next()){
                        unitText.setText(resultSet.getString("unit"));
                        totalbillText.setText(resultSet.getString("total_bill"));
                        statusText.setText(resultSet.getString("status"));

                    }
                }catch (Exception E){
                    E.printStackTrace();
                }
            }
        });

        pay = new JButton("Pay");
        pay.setBounds(100,460,100,25);
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);
        pay.addActionListener(this);
        add(pay);

        back = new JButton("Back");
        back.setBounds(400,460,100,25);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        back.addActionListener(this);
        add(back);



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == pay){
            try{
                database c = new database();
                c.statement.executeUpdate("update bill status = 'paid' where meterno = '"+meter+"' and month = '"+searchmonthChoise.getSelectedItem()+"'");
            }catch (Exception E){
                E.printStackTrace();
            }
            setVisible(false);
            new payment_bill(meter);

        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new pay_bill("");
    }
}
