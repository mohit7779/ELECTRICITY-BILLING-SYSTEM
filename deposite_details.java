package electricity.billing.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class deposite_details extends JFrame implements ActionListener {

    Choice searchMeterChoise,searchmonthChoise;

    JTable table;
    JButton search,print,close;


    deposite_details(){
        super("Deposite Details");


        JLabel searchMeter = new JLabel("Search by meter no.");
        searchMeter.setBounds(20,20,150,20);
        add(searchMeter);


        searchMeterChoise = new Choice();
        searchMeterChoise.setBounds(180,20,150,20);
        add(searchMeterChoise);

        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            while(resultSet.next()){
                searchMeterChoise.add(resultSet.getString("meterno"));
            }
        }catch (Exception E){
            E.printStackTrace();
        }





        JLabel searchName = new JLabel("Search by Name");
        searchName.setBounds(400,20,100,20);
        add(searchName);

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
        searchmonthChoise.setBounds(520,20,150,20);
        add(searchmonthChoise);



        table = new JTable();
        try{
            database c = new database();
            ResultSet resultSet = c.statement.executeQuery("select * from bill");
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        }catch (Exception E){
            E.printStackTrace();
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0,100,700,500);
        scrollPane.setBackground(Color.WHITE);
        add(scrollPane);



        search = new JButton("Search");
        search.setBackground(Color.WHITE);
        search.setBounds(20,70,80,20);
        search.addActionListener(this);
        add(search);

        print = new JButton("Print");
        print.setBackground(Color.WHITE);
        print.setBounds(120,70,80,20);
        print.addActionListener(this);
        add(print);

        close = new JButton("Close");
        close.setBackground(Color.WHITE);
        close.setBounds(600,70,80,20);
        close.addActionListener(this);
        add(close);



        getContentPane().setBackground(Color.CYAN);
        setSize(700,500);
        setLocation(400,150);
        setLayout(null);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == search){
            String query_search = "select * from bill where meterno = '"+searchMeterChoise.getSelectedItem()+"'and month = '"+searchmonthChoise.getSelectedItem()+"'" ;
            try{
                database c  = new database();
                ResultSet resultSet = c.statement.executeQuery(query_search);
                table.setModel(DbUtils.resultSetToTableModel(resultSet));
            }catch (Exception E){
                E.printStackTrace();
            }
        } else if (e.getSource() == print) {
            try{
                table.print();

            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }
    public static void main(String[] args) {
        new deposite_details();
    }
}
