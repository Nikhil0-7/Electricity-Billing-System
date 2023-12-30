package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class PayBill extends JFrame implements ActionListener{
    Choice cmonth;
    JButton pay, back;
    String meter;
    PayBill(String meter){
        this.meter = meter;
        setLayout(null);
        
        setBounds(360, 120, 850, 500);
        
        JLabel heading = new JLabel("Electicity Bill");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(120, 5, 400, 30);
        add(heading);
        
        JLabel lblmeternumber = new JLabel("Meter Number");
        lblmeternumber.setBounds(35, 80, 100, 20);
        add(lblmeternumber);
        
        JLabel meternumber = new JLabel("");
        meternumber.setBounds(190, 80, 100, 20);
        add(meternumber);
        
        JLabel lblname = new JLabel("Name");
        lblname.setBounds(35, 140, 100, 20);
        add(lblname);
        
        JLabel labelname = new JLabel("");
        labelname.setBounds(190, 140, 100, 20);
        add(labelname);
        
        JLabel lblmonth = new JLabel("Month");
        lblmonth.setBounds(35, 200, 100, 20);
        add(lblmonth);
        
        cmonth = new Choice();
        cmonth.setBounds(190, 200, 150, 20);
        cmonth.add("January");
        cmonth.add("February");
        cmonth.add("March");
        cmonth.add("April");
        cmonth.add("May");
        cmonth.add("June");
        cmonth.add("July");
        cmonth.add("August");
        cmonth.add("September");
        cmonth.add("October");
        cmonth.add("November");
        cmonth.add("December");
        add(cmonth);
        
        JLabel lblunits = new JLabel("Units");
        lblunits.setBounds(35, 260, 100, 20);
        add(lblunits);
        
        JLabel labelunits = new JLabel("");
        labelunits.setBounds(190, 260, 100, 20);
        add(labelunits);
        
        JLabel lbltotalbill = new JLabel("Total Bill");
        lbltotalbill.setBounds(35, 320, 100, 20);
        add(lbltotalbill);
        
        JLabel labeltotalbill = new JLabel("");
        labeltotalbill.setBounds(190, 320, 100, 20);
        add(labeltotalbill);
        
        JLabel lblstatus = new JLabel("Status");
        lblstatus.setBounds(35, 380, 100, 20);
        add(lblstatus);
        
        JLabel labelstatus = new JLabel("");
        labelstatus.setBounds(190, 380, 100, 20);
        labelstatus.setForeground(Color.RED);
        add(labelstatus);
        
        
        
       try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer where meter_no = '"+meter+"'");
            while(rs.next()) {
                meternumber.setText(meter);
                labelname.setText(rs.getString("name"));
            }
            
            rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = 'January'");
            while(rs.next()) {
                labelunits.setText(rs.getString("units"));
                labeltotalbill.setText(rs.getString("totalbill"));
                labelstatus.setText(rs.getString("status"));
            }
        } catch (SQLException e) {
        }
        
        cmonth.addItemListener((ItemEvent ae) -> {
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery("select * from bill where meter_no = '"+meter+"' AND month = '"+cmonth.getSelectedItem()+"'");
                while(rs.next()) {
                    labelunits.setText(rs.getString("units"));
                    labeltotalbill.setText(rs.getString("totalbill"));
                    labelstatus.setText(rs.getString("status"));
                }
            } catch (SQLException e) {
            }
        });
        
        pay = new JButton("Pay");
        pay.setBounds(100, 420, 100, 25);
        add(pay);
        back = new JButton("Back");
        back.setBounds(230, 420, 100, 25);
        add(back);
        
        pay.setBackground(Color.BLACK);
        pay.setForeground(Color.WHITE);

        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/bill.png"));
        Image i2 = i1.getImage().getScaledInstance(600, 300,Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel l21 = new JLabel(i3);
        l21.setBounds(400, 120, 550, 300);
        add(l21);
        
        pay.addActionListener(this);
        back.addActionListener(this);
        
        getContentPane().setBackground(Color.WHITE);        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == pay) {
            try {
                Conn c = new Conn();
                c.s.executeUpdate("update bill set status = 'Paid' where meter_no = '"+meter+"' AND month='"+cmonth.getSelectedItem()+"'");
            } catch (SQLException e) {
            }
            setVisible(false);
            new Paytm(meter){};
        } else {
            setVisible(false);
        }
    }

    
       
    public static void main(String[] args){
        new PayBill("").setVisible(true);
    }
}
