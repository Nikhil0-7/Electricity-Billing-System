package Electricity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.sql.*;

public class Signup extends JFrame implements ActionListener {
    
    JButton create, back;
    Choice accountType;
    JTextField meter, user, name1, password1;
    
    Signup(){
        setBounds(400, 200, 600, 350);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        
        JPanel panel = new JPanel();
        panel.setBounds(20, 20, 550, 280);
        panel.setBorder(new TitledBorder(new LineBorder( new Color(0, 255,255)), "Create-Account", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 255, 255) ));
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        panel.setForeground(new Color(34, 139, 34));
        add(panel);
        
        JLabel heading = new  JLabel ("Create Account As");
        heading.setBounds(80, 40, 140, 20);
        heading.setFont(new Font("Thoma", Font.BOLD, 14));
        panel.add(heading);
        
        accountType = new Choice();
        accountType.add("Admin");
        accountType.add("Customer");
        accountType.setBounds(220, 40, 140, 20);
        panel.add(accountType);
        
        
        
        JLabel lblmeter = new  JLabel ("Meter number");
        lblmeter.setBounds(80, 80, 140, 20);
        lblmeter.setFont(new Font("Thoma", Font.BOLD, 14));
        lblmeter.setVisible(false);
        panel.add(lblmeter);
        
        meter = new JTextField();
        meter.setBounds(220, 80, 150,20);
        meter.setVisible(false);
        panel.add(meter);
        
        JLabel username = new  JLabel ("Username");
        username.setBounds(80, 120, 140, 20);
        username.setFont(new Font("Thoma", Font.BOLD, 14));
        panel.add(username);
        
        user = new JTextField();
        user.setBounds(220, 120, 150,20);
        panel.add(user);
        
        JLabel name = new  JLabel ("Name");
        name.setBounds(80, 160, 140, 20);
        name.setFont(new Font("Thoma", Font.BOLD, 14));
        panel.add(name);
        
        name1= new JTextField();
        name1.setBounds(220, 160, 150,20);
        panel.add(name1);
        
        meter.addFocusListener(new FocusListener(){
         
            @Override
            public void focusGained(FocusEvent fe){}
            
            @Override
            public void focusLost(FocusEvent fe){
                try{
                    Conn c =new Conn();//creating connect class object 
                    ResultSet rs = (ResultSet) c.s.executeQuery("select * from login where meter_no = '"+meter.getText()+"'");
                    while(rs.next()){
                        name1.setText(rs.getString("name"));
                    }
                }catch (SQLException e){
                }
            }
        });
        
        JLabel password = new  JLabel ("Password");
        password.setBounds(80, 200, 140, 20);
        password.setFont(new Font("Thoma", Font.BOLD, 14));
        panel.add(password);
        
        password1= new JTextField();
        password1.setBounds(220, 200, 150,20);
        panel.add(password1);
        
         accountType.addItemListener((ItemEvent ae) -> {
             String user1 = accountType.getSelectedItem();
            if (user1.equals("Customer")) {
                lblmeter.setVisible(true);
                meter.setVisible(true);
                name1.setEditable(false);
            } else {
                lblmeter.setVisible(false);
                meter.setVisible(false);
                name1.setEditable(true);
            }
        });
        
        create = new JButton("Create");
        create.setBounds(100, 240, 110, 20);
        create.addActionListener(this);
        panel.add(create);
        
        back = new JButton("back");
        back.setBounds(250, 240, 110, 20);
        back.addActionListener(this);
        panel.add(back);
        
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icon/signupImage.png"));
        Image i8 = i7.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel image = new JLabel(i9);
        image.setBounds(370, 30, 200, 200);
        panel.add(image);
                
                
        setVisible(true);        
    }
    @Override
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource() == create){
            String atype = accountType.getSelectedItem();
            String susername = user.getText();
            String sname = name1.getText();
            String spassword = password1.getText();
            String smeter = meter.getText();
            
            try{
                Conn c = new Conn();
                
                String query = null;
                if(atype.equals("Admin")){
                    query="insert into login values('"+smeter+"', '"+susername+"', '"+sname+"', '"+spassword+"', '"+atype+"')";
                }else{
                     query="update login set username ='"+susername+"', password = '"+spassword+"', user = '"+atype+"' where meter_no = '"+smeter+"'";
                }
                c.s.executeUpdate(query);
                
                JOptionPane.showMessageDialog(null, "Account Created Successfully");
                setVisible(false);
                new Login(){};
            }catch (HeadlessException | SQLException e){
            }
        }
        else if(ae.getSource() == back){
         setVisible(false);
            
         new Login(){};
        }
        
    }
    
    public static void main(String args[]){
        new Signup(){};
    }

}

