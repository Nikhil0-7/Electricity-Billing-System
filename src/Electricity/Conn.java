package Electricity;

import java.sql.*;  

public class Conn{
    Connection c;
    Statement s;
    public Conn(){  
        try{ 
            
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebs", "root", "Nikhil07");
            s = c.createStatement();// creating statement
            
           
        }catch(SQLException e){ 
            System.out.println(e);
        }  
    }  
}  