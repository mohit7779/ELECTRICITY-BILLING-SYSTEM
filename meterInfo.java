package electricity.billing.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class meterInfo extends JFrame implements ActionListener {

    Choice meterLocChoise,metertypeChoise,phaseCodeCho,BilltypCho;
    JButton submit;

    String meternumber;
    meterInfo(String meternumber){
        this.meternumber = meternumber;
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.CYAN);
        add(panel);

        JLabel heading = new JLabel("Meter Information");
        heading.setBounds(180,10,200,20);
        heading.setFont(new Font("Tahome",Font.BOLD,20));
        panel.add(heading);


        JLabel meterNO = new JLabel("Meter Number");
        meterNO.setBounds(50,120,100,20);
        panel.add(meterNO);


        JLabel meterNOText = new JLabel("meternumber");
        meterNOText.setBounds(180,80,150,20);
        panel.add(meterNOText);

        JLabel meterLoc = new JLabel("Meter Number");
        meterLoc.setBounds(50,120,100,20);
        panel.add(meterLoc);

        meterLocChoise = new Choice();
        meterLocChoise.add("Outside");
        meterLocChoise.add("Inside");
        meterLocChoise.setBounds(180,120,150,20);
        panel.add(meterLocChoise);


        JLabel metertype = new JLabel("Meter type");
        metertype.setBounds(50,160,100,20);
        panel.add(metertype);

        metertypeChoise = new Choice();
        metertypeChoise.add("Electric meter");
        metertypeChoise.add("Solar meter");
        metertypeChoise.add("Smart meter");
        metertypeChoise.setBounds(180,160,150,20);
        panel.add(metertypeChoise);


        JLabel phaseCode = new JLabel("Phase Code");
        phaseCode.setBounds(50,200,100,20);
        panel.add(phaseCode);

        phaseCodeCho = new Choice();
        phaseCodeCho.add("011");
        phaseCodeCho.add("022");
        phaseCodeCho.add("033");
        phaseCodeCho.add("044");
        phaseCodeCho.add("055");
        phaseCodeCho.add("066");
        phaseCodeCho.add("077");
        phaseCodeCho.add("088");
        phaseCodeCho.add("099");
        phaseCodeCho.setBounds(180,200,150,20);
        panel.add(phaseCodeCho);

        JLabel billtyp = new JLabel("Bill Type");
        billtyp.setBounds(50,240,100,20);
        panel.add(billtyp);

        BilltypCho = new Choice();
        BilltypCho.add("Industrial");
        BilltypCho.add("Normal");
        BilltypCho.setBounds(180,240,150,20);
        panel.add(BilltypCho);


        JLabel days = new JLabel("30 Days Billing Time...");
        days.setBounds(50,280,150,20);
        panel.add(days);

        JLabel note = new JLabel("Note:-");
        note.setBounds(50,320,100,20);
        panel.add(note);

        JLabel note1 = new JLabel("By Default bill is calculated for 30 days only");
        note1.setBounds(50,340,300,20);
        panel.add(note1);

        submit = new JButton("Submit");
        submit.setBounds(220,390,100,25);
        submit.setBackground(Color.BLACK);
        submit.setForeground(Color.WHITE);
        submit.addActionListener(this);
        panel.add(submit);


        setLayout(new BorderLayout());
        add(panel,"Center");

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/details.png"));
        Image i2 = i1.getImage().getScaledInstance(250,200,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel imageLabel = new JLabel(i3);
        add(imageLabel,"East");





        setSize(700,500);
        setLocation(400,150);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == submit){
            String smeterNum = meternumber;
            String smeterLocCho = meterLocChoise.getSelectedItem();
            String smeterTyp = metertypeChoise.getSelectedItem();
            String sphasecode = phaseCodeCho.getSelectedItem();
            String sbilltyp = BilltypCho.getSelectedItem();
            String sday = "30";

            String query_meterinfo = "insert into meterinfo value('"+smeterNum+"','"+smeterLocCho+"','"+smeterTyp+"','"+sphasecode+"','"+sbilltyp+"','"+sday+"')";
            try{
                database c = new database();
                c.statement.executeUpdate(query_meterinfo);

                JOptionPane.showMessageDialog(null,"meter info submited successfully");
                setVisible(false);
            }catch (Exception E){
                E.printStackTrace();
            }
        }else{
            setVisible(false);
        }
    }

    public static void main(String[] args) {
        new meterInfo("");
    }
}
