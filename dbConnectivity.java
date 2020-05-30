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

import java.sql.Connection;
import java.sql.DriverManager;   
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
public class dbConnectivity {
    Connection con;
    Statement stmt;
   
    dbConnectivity() //cons
    {
        try
        {
            
             String connection = "jdbc:sqlserver://localhost:1433;databaseName=SapphireStore";
                con= (Connection) DriverManager.getConnection(connection,"noorish","imamah");
            System.out.println("Connected");

            stmt = con.createStatement();
           
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
}
