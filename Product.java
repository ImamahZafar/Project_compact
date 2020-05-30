/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author imamah
 */
public abstract class Product {
    int ID;
    String Code;
    int Price;

    public Product(int ID, String Code, int Price) {
        this.ID = ID;
        this.Code = Code;
        this.Price = Price;
    }
  

    Product()
    {
     
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }
    
    abstract void getProduct(int i);
    abstract int getProductPrice(int id);
}
