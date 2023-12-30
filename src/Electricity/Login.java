package Electricity;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Login extends JFrame implements ActionListener {
    JButton login, signup, cancel;
    JTextField username, password;
    Choice loggingin;
    Login(){
        super("Login Page");
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        //username label 
        JLabel lblUsername = new  JLabel ("Username");
        lblUsername.setBounds(250, 20, 100, 20);
        add(lblUsername);
        
        //username text field
        username = new JTextField();
        username.setBounds(325, 20, 150, 20);
        add(username);
        
        //password label
        JLabel lblPassword = new  JLabel ("Password");
        lblPassword.setBounds(250, 60, 100, 20);
        add(lblPassword);
        
        //Password Text Field
        password = new JTextField();
        password.setBounds(325, 60, 150, 20);
        add(password);
        
        //Logging As label
        JLabel logginginas = new  JLabel ("Logging in as");
        logginginas.setBounds(250, 100, 100, 20);
        add(logginginas);
        
        //Logging as choice field
        loggingin = new Choice();
        loggingin.add("");
        loggingin.add("Admin");
        loggingin.add("Customer");
        loggingin.setBounds(350, 100, 150, 20);
        add(loggingin);
        
        //Login Button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icon/login.png"));
        Image i2 = i1.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        login = new JButton("Login", new ImageIcon(i2));
        login.setBounds(300, 160, 100, 20);
        login.addActionListener(this);
        add(login);
        
        //cancel Button
        ImageIcon i3 = new ImageIcon(ClassLoader.getSystemResource("icon/cancel.jpg"));
        Image i4 = i3.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        cancel = new JButton("Cancel", new ImageIcon(i4));
        cancel.setBounds(420, 160, 100, 20);
        cancel.addActionListener(this);
        add(cancel);
        
        //SignUp  Button
        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("icon/signup.png"));
        Image i6 = i5.getImage().getScaledInstance(16, 16, Image.SCALE_DEFAULT);
        signup = new JButton("Signup", new ImageIcon(i6));
        signup.setBounds(360, 200, 100, 20);
        signup.addActionListener(this);
        add(signup);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/second.jpg"));
        Image i8 = i7.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(0, 0, 250, 250);
        add(image);
        
        
        setSize(600,350);
        setLocation(400,200);
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == login){
            String susername = username.getText();
            String spassword = password.getText();
            String user = loggingin.getSelectedItem();
            
            try{
                Conn c = new Conn();
                String query = "select * from login where username = '"+susername+"' and password = '"+spassword+"' and user = '"+user+"'";
                
               ResultSet rs = (ResultSet) c.s.executeQuery(query);
               
               if(rs.next()){
                   String meter = rs.getString("meter_no");
                   setVisible(false);
                   new Project(user,meter){};
               }else{
                   JOptionPane.showMessageDialog(null, "OOps! Invalid Login");
                   username.setText("");
                   password.setText("");
               }
                
            } catch(SQLException e){
                
            }
            
        }else if(ae.getSource() == cancel){
            setVisible(false);
        }else if(ae.getSource() == signup){
             setVisible(false);
             
             new Signup(){};
        }
    }
    
    public static void main(String[] args){
        new Login(){};
    }
}



