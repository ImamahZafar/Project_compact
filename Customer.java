/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package project;

import java.sql.Date;  
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author imamah
 */
public class Customer {
    String name;
    String email;
    String password;
    String address;
    
    
    Connection customerCon;
    Statement customerStmt;

    public Customer ()
    {
        try{
     
        String connection = "jdbc:sqlserver://localhost:1433;databaseName=SapphireStore";
        this.customerCon= (Connection) DriverManager.getConnection(connection,"noorish","imamah");
        

        this.customerStmt = (Statement) this.customerCon.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
    }
    public Customer(String name, String email, String password, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    void Signup(String email, String name, String password, String Address)
    {
        int Status=1;
          try{
                String query="insert into Customer values(?,?,?,?,?)";
                PreparedStatement preparedStmt = (PreparedStatement) this.customerCon.prepareStatement(query);
            
                preparedStmt.setString(1,email);
                preparedStmt.setString(2, name);
                preparedStmt.setString(3, password);
                preparedStmt.setString(4, Address);
                preparedStmt.setInt(5, Status);
                preparedStmt.executeUpdate();
                
            }
            catch(Exception e){
                  System.out.println(email+ name+password+Address);
                System.out.println(e);
            }
    }
    
    Customer displayProfile(String email)
    {
        
           try{
            String s = "select * from Customer where Email=?";
           PreparedStatement preparedStmt = this.customerCon.prepareStatement(s);
            preparedStmt.setString(1, email);
                ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                this.email=rs.getString(1);
                this.name=rs.getString(2);
               this.password=rs.getString(3);
                this.address=rs.getString(4);
                   }
        }
        catch(Exception e){
            System.out.println(e);
        }
        return this;  
    }
    
    public boolean verify(String email, String pass)
    {
  
        try{
            
            String query =  "select * from Customer where Email = ?  and Password=? ";
        PreparedStatement preparedStmt = this.customerCon.prepareStatement(query);
            preparedStmt.setString(1, email);
            preparedStmt.setString(2, pass);
            
            ResultSet rs = preparedStmt.executeQuery();
            
            if(rs.next() == false)
            {
                System.out.println(" rs is false Incorrect Credentials. \n");
                return false;
            }
            if( rs.getString(1).equals(email) )
            {
                if(rs.getString(3).equals(pass))
                        System.out.println( rs.getString(3).equals(pass));
                return true;
            }
            else 
            {
                System.out.println("Incorrect Credentials.  \n");
                System.out.println(rs.getString(1)+ rs.getString(3));
                  System.out.println(email+ pass);
                 System.out.println( rs.getString(3).equals(pass));
                
                return false;
            }
             }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return false;
    }
 
   void signOut(String customerEmail)
   {
       try{
             

                PreparedStatement preparedStmt = (PreparedStatement) customerCon.prepareStatement("UPDATE Customer SET Status = ? WHERE Email = ?");
          int status =0;
                preparedStmt.setInt(1, status);
                 preparedStmt.setString (2, customerEmail);
                preparedStmt.executeUpdate();
                Cart c=new Cart();
                c.emptyCart(customerEmail);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
   
}
