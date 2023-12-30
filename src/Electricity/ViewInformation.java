package Electricity;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import javax.swing.*;
public class ViewInformation extends JFrame implements ActionListener{
    JButton cancel;;
    
    ViewInformation(String meter){
        setBounds(360, 120, 850, 500);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JLabel heading = new JLabel("VIEW CUSTOMER INFORMATION");
        heading.setBounds(250, 0, 500, 40);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 20));
        add(heading);
        
        
        JLabel lblname = new JLabel("Name:");
        lblname.setBounds(70, 50, 100, 20);
        add(lblname);
        
        JLabel name = new JLabel("");
        name.setBounds(200, 50, 100, 20);
        add(name);
        
        JLabel lblmeternumber = new JLabel("Meter Number:");
        lblmeternumber.setBounds(70, 90, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(200, 90, 100, 20);
        add(meternumber);
        
        JLabel lbladdress = new JLabel("Address:");
        lbladdress.setBounds(70, 130, 100, 20);
        add(lbladdress);
        
        JLabel address = new JLabel("");
        address.setBounds(200, 130, 120, 20);
        add(address);
        
        JLabel lblcity = new JLabel("City:");
        lblcity.setBounds(70, 170, 100, 20);
        add(lblcity);
        
        JLabel city = new JLabel("");
        city.setBounds(200, 170, 100, 20);
        add(city);
        
        JLabel lblstate = new JLabel("State:");
        lblstate.setBounds(500, 50, 100, 20);
        add(lblstate);
        
        JLabel state = new JLabel("");
        state.setBounds(600, 50, 100, 20);
        add(state);
        
        JLabel lblemail = new JLabel("Email:");
        lblemail.setBounds(500, 90, 100, 20);
        add(lblemail);
        
        JLabel email = new JLabel("");
        email.setBounds(600, 90, 120, 20);
        add(email);
        
        JLabel lblphone = new JLabel("Phone:");
        lblphone.setBounds(500, 130, 100, 20);
        add(lblphone);
        
        JLabel phone = new JLabel("");
        phone.setBounds(600, 130, 100, 20);
        add(phone);
        
         try {
            Conn c = new Conn();
            ResultSet rs = (ResultSet) c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()) {
                name.setText(rs.getString("name"));
                address.setText(rs.getString("address"));
                city.setText(rs.getString("city"));
                state.setText(rs.getString("state"));
                email.setText(rs.getString("email"));
                phone.setText(rs.getString("phone"));
                meternumber.setText(rs.getString("meter_no"));
            }
        } catch (SQLException e) {
        }
        
        cancel = new JButton("Cancel");
        cancel.setBackground(Color.BLACK);
        cancel.setForeground(Color.WHITE);
        cancel.setBounds(350, 220, 100, 25);
        add(cancel);
        cancel.addActionListener(this);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/viewcustomer.jpg"));
        Image i2 = i1.getImage().getScaledInstance(600, 250, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel image = new JLabel(i3);
        image.setBounds(20, 200, 600, 300);
        add(image);
        
        setVisible(true);
    }
    
    @Override
     public void actionPerformed(ActionEvent ae) {
        setVisible(false);
    }
    
    public static void main(String[] args){
        new ViewInformation(""){};
    }
    
}