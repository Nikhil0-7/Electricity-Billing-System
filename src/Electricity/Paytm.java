package Electricity;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*;

public class Paytm extends JFrame implements ActionListener{
    String meter;
    JButton back;
    Paytm(String meter) {
        this.meter = meter;
        
        JEditorPane j = new JEditorPane();
        j.setEditable(false);
        
        try {
            j.setPage("https://paytm.com/online-payments");
        } catch (IOException e) {
            j.setContentType("text/html");
            j.setText("<html>Could not load<html>");
            
        }
        
        JScrollPane pane = new JScrollPane(j);
        add(pane);
        
        back = new JButton("Back");
        back.setBounds(640, 20, 80, 30);
        back.addActionListener(this);
        j.add(back);
        
        setSize(800,500);
        setLocation(300, 100);
        setVisible(true);
        
    }
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new PayBill(meter){};
    }
    public static void main(String[] args){
        new Paytm("").setVisible(true);
    }
}
