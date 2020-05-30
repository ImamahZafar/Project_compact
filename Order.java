/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author imamah
 */
public class Order {
    String email;
    Cart cart;
    int orderNum;
    String status;
        Connection customerCon;
    Statement customerStmt;
    
    Order()
    {
         try{
     
        String connection = "jdbc:sqlserver://localhost:1433;databaseName=SapphireStore";
        this.customerCon= (java.sql.Connection) DriverManager.getConnection(connection,"noorish","imamah");
        

        this.customerStmt = (java.sql.Statement) this.customerCon.createStatement(); 
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }
     int checkout(String email)
    {
        int x=0;
          try{
                String query="insert into CustomerOrder values(?,?,?,?)";
                PreparedStatement preparedStmt = (PreparedStatement) this.customerCon.prepareStatement(query);
            x= (int)Math.floor(Math.random()*1000000);
                System.out.println(x);
                preparedStmt.setInt(1,x);
                preparedStmt.setString(2, email);
               Cart c=new Cart();
                int bill=c.calculateBill(email);
                 preparedStmt.setInt(3,bill);
                 String status=new String("Order Confirmed");
                  preparedStmt.setString(4, status);

                preparedStmt.executeUpdate();
                
            }
            catch(Exception e){
               
                System.out.println(e);
            }
          return x;
    }
     
       String getStatus(String email,int orderNum)
    {
        
           try{
            String s = "select OrderStatus from CustomerOrder where CustomerEmail=? and OrderId=?";
         java.sql.PreparedStatement preparedStmt = this.customerCon.prepareStatement(s);
             preparedStmt.setString (1, email);
              preparedStmt.setInt (2, orderNum);
             ResultSet rs = preparedStmt.executeQuery();
           
             while(rs.next())
             {
             
                status= rs.getString(1);

             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return status;
    }
}

