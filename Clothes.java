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
public class Clothes extends Product{
    int QuantitiyOfSmall;
    int QuantitiyOfmedium;
    int QuantitiyOflarge;
        
    Connection customerCon;
    Statement customerStmt;
    Clothes()
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

    public Clothes( int ID, String Code, int Price) {
        super(ID, Code, Price);
   
    }

 

    @Override
   void getProduct(int i) {
             try{
            String s = "select * from Clothes where ID=?";
           PreparedStatement preparedStmt = this.customerCon.prepareStatement(s);
            preparedStmt.setInt(1, i);
                ResultSet rs = preparedStmt.executeQuery();
            while (rs.next()){
                this.ID=rs.getInt(1);
                this.Code=rs.getString(2);
               this.Price=rs.getInt(3);
                this.QuantitiyOfSmall=rs.getInt(4);
                this.QuantitiyOfmedium=rs.getInt(5);
                 this.QuantitiyOflarge=rs.getInt(6);

                   }
        }
        catch(Exception e){
                System.out.println("help");
            System.out.println(e);
        }
        }
    @Override
     int getProductPrice(int productId)
    {
        try{
             String query1 = "select Price from Clothes where ID=?";
             PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             preparedStmt.setInt (1, productId);
             ResultSet rs = preparedStmt.executeQuery();
           
             while(rs.next())
             {
                Price= rs.getInt(1);
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return Price;
    }
     void ReduceQuantityInStore(int productId, int size,int quantity)
    {
        try{
            String query1;
            if(size==1)
              query1 = "update QuantityOfSmallSize from Clothes where ID=?";
            else if (size==2)
                query1 = "update QuantityOfMediumSize from Clothes where ID=?";
            else
                query1 = "update QuantityOfLargeSize from Clothes where ID=?";
             PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             quantity--;
             preparedStmt.setInt (1, quantity);
             preparedStmt.setInt (2, productId);
           preparedStmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
       
    } 
     
}
