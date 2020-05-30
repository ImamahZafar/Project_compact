/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author imamah
 */
public class Cart {
    String customerEmail;
    Product product;
    int ItemId;
    int Quantity;
    int Price;
 Connection customerCon;
    Statement customerStmt;
   Cart()
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

    boolean checkIfProductExistsInCart(int productId)
    {
        try{
             String query1 = "select ItemID from Cart where ItemID=?";
             java.sql.PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             preparedStmt.setInt (1, productId);
             ResultSet rs = preparedStmt.executeQuery();
           
             while(rs.next())
             {
                 System.out.println("Product exists");
                         
                return true;
             }
             return false;
        }
        catch(Exception e)
        {
            System.out.println(e);
            return false;
            
        }
    }
     int getCartQuanitity(String customerEmail,int productId)
    {
        try{
             String query1 = "select Quantity from Cart where ItemID=? and CustomerEmail=?";
             java.sql.PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
             preparedStmt.setInt (1, productId);
              preparedStmt.setString (2, customerEmail);
             ResultSet rs = preparedStmt.executeQuery();
           
             while(rs.next())
             {
             
                Quantity= rs.getInt(1);

             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return Quantity;
    }
      int getTotalItemsInCart(String customerEmail)
    {
         int totalquantity=0;
        try{
           
             String query1 = "select Quantity from Cart where  CustomerEmail=?";
             java.sql.PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
              preparedStmt.setString (1, customerEmail);
             ResultSet rs = preparedStmt.executeQuery();
           
              while(rs.next())
             {
                totalquantity += rs.getInt(1);
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return totalquantity;
    }
       int calculateBill(String customerEmail)
    {
         int bill=0;
        try{
           
             String query1 = "select Price from Cart where  CustomerEmail=?";
             java.sql.PreparedStatement preparedStmt = this.customerCon.prepareStatement(query1);
              preparedStmt.setString (1, customerEmail);
             ResultSet rs = preparedStmt.executeQuery();
             
              while(rs.next())
             {
                bill += rs.getInt(1);
             }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return bill;
    }
     void addToCart(String customerEmail,  int productId,String productCode)
    {
     int newquantity=0;
               newquantity=getCartQuanitity(customerEmail,productId);
               if(newquantity==0)
                   addNewItemToCart(customerEmail,productId,productCode);
               else 
                UpdateItemInCart(customerEmail,productId,newquantity,productCode);
    
    }
         void addNewItemToCart(String customerEmail,  int productId,String productCode)
    {
        try{
                String query = "insert into Cart values(?, ?,?,?,?)";
                PreparedStatement preparedStmt = (PreparedStatement) customerCon.prepareStatement(query);
                preparedStmt.setString (1, customerEmail);
                preparedStmt.setInt  (2, productId);
                 preparedStmt.setString (3, productCode);
                Quantity=1;
                preparedStmt.setInt(4, Quantity);
                int price=0;
                ProductFactory pFactory=new ProductFactory();
                char firstChar=productCode.charAt(0);
                char secChar=productCode.charAt(1);
                if(firstChar=='A' && secChar =='X')
                     product=pFactory.displayItems("Acessories");
                else
                  product=pFactory.displayItems("Clothes");
                price= product.getProductPrice(productId);
                preparedStmt.setInt  (5, price);
                preparedStmt.executeUpdate();
             
             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
          void UpdateItemInCart(String customerEmail,  int productId,int newQuantity,String productCode)
    {
        try{
             

                PreparedStatement preparedStmt = (PreparedStatement) customerCon.prepareStatement("UPDATE Cart SET Quantity = ?, Price = ? WHERE CustomerEmail = ? AND ItemID=?;");
              
               
                newQuantity++;
                preparedStmt.setInt(1, newQuantity);
                int price=0;
                ProductFactory pFactory=new ProductFactory();
                char firstChar=productCode.charAt(0);
                char secChar=productCode.charAt(1);
                if(firstChar=='A' && secChar =='X')
                     product=pFactory.displayItems("Acessories");
                else
                  product=pFactory.displayItems("Clothes");
                price= product.getProductPrice(productId)*newQuantity;
                preparedStmt.setInt  (2, price);
                 preparedStmt.setString (3, customerEmail);
                preparedStmt.setInt  (4, productId);
                preparedStmt.executeUpdate();

             
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
    List<Product> displayCart(String email)
    {
          List<Product> itemsInCart=new ArrayList<>();
        try{
            String s = "select * from Cart where  CustomerEmail=?";
           PreparedStatement preparedStmt = this.customerCon.prepareStatement(s);
            preparedStmt.setString(1, email);
            ResultSet rs = preparedStmt.executeQuery();
          
            
            while (rs.next()){
                     String email1 =rs.getString(1);
                      int id=  rs.getInt(2);
                      String productCode =rs.getString(3);
                      int quantity=rs.getInt(4);
                      int price= rs.getInt(5);
                   
                      Product product=new Clothes(id,productCode,price);
                      itemsInCart.add(product);
                   }
            return itemsInCart;
        }
        catch(Exception e){
            System.out.println(e);
        }  return itemsInCart;
    }
     void remove(int ItemId,String email)
    {
        try{
             String query = "delete from Cart where ItemID = ? and CustomerEmail=?";
             PreparedStatement preparedStmt = this.customerCon.prepareStatement(query);
             preparedStmt.setInt (1, ItemId);
             preparedStmt.setString (2, email);
             preparedStmt.executeUpdate();
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
      void emptyCart(String customerId){
       
        try
        {
             String s = "select ItemID from Cart where CustomerEmail=?";
             PreparedStatement preparedStmt1 = this.customerCon.prepareStatement(s);
             preparedStmt1.setString(1, customerId);
             ResultSet rs = preparedStmt1.executeQuery();
             while(rs.next())
             {
                 int Id  = rs.getInt(1);
                 remove(Id,customerId);
             }
     
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    
}
