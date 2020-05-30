/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

/**
 *
 * @author imamah
 */
public class ProductFactory {
    ProductFactory()
    {
        
    }
           
    
    public Product displayItems( String TypeOfProduct)
    {
        if(TypeOfProduct.equalsIgnoreCase("Acessories"))
        {
            return new Acessories();
        }
        else if(TypeOfProduct.equalsIgnoreCase("Clothes"))
        {
            return new Clothes();
        }
        return null;
    }
}
