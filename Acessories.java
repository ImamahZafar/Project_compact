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
public class Acessories extends Product {
    int QuantityInStore;
            
    Connection customerCon;
    Statement customerStmt;
    Acessories()
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
   
int getQuantity()
{
    return this.QuantityInStore;
}
    @Override
    int getProductPrice(int id) {
   
       try{
             String query1 = "select Price from Acessories where ID=?";
             PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             preparedStmt.setInt (1, id);
             ResultSet rs = preparedStmt.executeQuery();
           
             while(rs.next())
             {
                Price= rs.getInt(1);
                System.out.println(Price);
             }
              System.out.println(Price);
        }
        catch(Exception e)
        {
              System.out.println("wrror");
            System.out.println(e);
        }
        return Price;  
    }

    @Override
    void getProduct(int i) {
        try{
            String s = "select * from Acessories where ID=?";
           PreparedStatement preparedStmt = this.customerCon.prepareStatement(s);
            preparedStmt.setInt(1, i);
                ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                this.ID=rs.getInt(1);
                this.Code=rs.getString(2);
               this.Price=rs.getInt(3);
                this.QuantityInStore=rs.getInt(4);
             System.out.println(ID+Code+Price+QuantityInStore);

                   }
        }
        catch(Exception e){
            System.out.println(e);
        }
    }
       void ReduceQuantityInStore(int productId, int quantity)
    {
        try{
            String query1;
              query1 = "update QuantityInStore from Acessories where ID=?";
          System.out.println(quantity);
             PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             quantity--;
             preparedStmt.setInt (1, quantity);
             preparedStmt.setInt (2, productId);
             System.out.println(quantity);
           preparedStmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
    } 
}
