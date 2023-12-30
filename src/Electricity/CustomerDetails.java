package Electricity;

import java.awt.event.*;
import javax.swing.*;
import java.sql.*;
import net.proteanit.sql.DbUtils;
import java.awt.print.PrinterException;

public class CustomerDetails extends JFrame implements ActionListener {
    
    
    JTable table;
    JButton  print;
    
    CustomerDetails(){
        super("Customer Details");
        
        setSize(700, 550);
        setLocation(300, 100);
        table = new JTable();
        
        try {
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery("select * from customer");
            
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (SQLException e) {
        }
        
        JScrollPane sp = new JScrollPane(table);
        add(sp);
        
        print = new JButton("Print");
        print.addActionListener(this);
        add(print, "South");
        
        
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            table.print();
        } catch (PrinterException e) {
        }
    }
    
    public static void main(String[] args){
         new CustomerDetails(){};
    }
}