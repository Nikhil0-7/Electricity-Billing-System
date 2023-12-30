package Electricity;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.print.PrinterException;

public class DepositDetails extends JFrame implements ActionListener {
    Choice meternumber, months;
    JTable table;
    JButton search, print;
    
    DepositDetails(){
        
        super("Deposit Details");
        
        setSize(700, 550);
        setLocation(300, 100);
        
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        
        JLabel lblmeternumber = new JLabel("Search By Meter Number");
        lblmeternumber.setBounds(20, 20, 150, 20);
        add(lblmeternumber);
        
        meternumber = new Choice();
        try{
            Conn c = new Conn();
           ResultSet rs= (ResultSet) c.s.executeQuery("select * from customer");
           while(rs.next()){
              meternumber.add(rs.getString("meter_no"));
           }
        } catch(SQLException e){}
        meternumber.setBounds(180, 20, 100, 20);
        add(meternumber);
        
        JLabel lblmonth = new JLabel("Search By Month");
        lblmonth.setBounds(380, 20, 100, 20);
        add(lblmonth);
        
        months = new Choice();
        months.add("");
        months.add("January");
        months.add("February");
        months.add("March");
        months.add("April");
        months.add("May");
        months.add("June");
        months.add("July");
        months.add("August");
        months.add("September");
        months.add("October");
        months.add("November");
        months.add("December");
        months.setBounds(500, 20, 120, 20);
        add(months);
        
        table = new JTable();
        
        try{
            Conn c=new Conn();
            ResultSet rs = c.s.executeQuery("select * from bill");
            
           table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e){
        }
        
        
        JScrollPane sp=new  JScrollPane(table);
        sp.setBounds(0, 100, 700, 600);
        add(sp);
        
        
        search = new JButton("search");
        search.setBounds(20, 70, 80, 20);
        search.setBackground(Color.BLACK);
        search.setForeground(Color.WHITE);
        search.addActionListener(this);
        add (search);
        
        print = new JButton("print");
        print.setBounds(110, 70, 80, 20);
        print.setBackground(Color.BLACK);
        print.setForeground(Color.WHITE);
        print.addActionListener(this);
        add(print);
        
        
        setVisible(true);
    }
   
    @Override
     public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == search){
            
             String query = "select * from bill where meter_no = '"+meternumber.getSelectedItem()+"' and month = '"+months.getSelectedItem()+"'";
            
            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);
                table.setModel(DbUtils.resultSetToTableModel(rs));
            } catch (SQLException e) {
                
            }
                   
        }else{
            try{
                table.print();
            }catch (PrinterException e){
            }
            }
        } 
    
    public static void main(String[] args){
        new DepositDetails(){};
    }
}
