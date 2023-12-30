package Electricity;

import javax.swing.*;
import java.awt.*;

public abstract class Splash extends JFrame implements Runnable{ //using swing 
    Thread t;
    Splash(){
        super("Electricity Billing System");
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/elect.jpg"));//taking the image from icon folder in i1.
        
        Image i2 = i1.getImage().getScaledInstance(730, 550, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        
        JLabel image = new JLabel(i3);
        
        add(image);
        setVisible(true);
        
        //setting size and speed of image 
        int x=1;
        for(int i =2; i<600; i+=4, x+=1){
            setSize(i+x, i);//seting the height width of frame for image.
            setLocation(650-((i+x)/2), 350-(i/2));//seting the location of frame on screen.
        }
        
        try{
            Thread.sleep(20);
        }catch (InterruptedException e){
        }
        
        t=new Thread(this);
        t.start();
    }
    
    @Override
    public void run(){
        try{
            Thread.sleep(5000);//Hold for 5sec.
            setVisible(false);
            
            //login frame
            new Login(){};
        }catch (InterruptedException e){
        }
    }
    
    public static void main(String[] args){
      new Splash() {};
    }
}
