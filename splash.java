package electricity.billing.system;

import javax.swing.*;
import java.awt.*;


public class splash extends JFrame {
    splash(){

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/Splash.jpg"));
        Image imageOne = imageIcon.getImage().getScaledInstance(600,400, Image.SCALE_DEFAULT);
        ImageIcon imageIcon2 = new ImageIcon(imageOne);
        JLabel imageLabel = new JLabel(imageIcon2);
        add(imageLabel);

        setSize(600,400);
        setLocation(400,200);
        setVisible(true);

        try{
            Thread.sleep(3000);
            setVisible(false);

            new login();
        }catch (Exception e){
            e.printStackTrace();

        }



    }
    public static void main(String[] args) {

        new splash();
    }
}
