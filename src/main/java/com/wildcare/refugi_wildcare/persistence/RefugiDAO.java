/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wildcare.refugi_wildcare.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author joellopez
 */
public class RefugiDAO {
    
    private static RefugiDAO inst;
    
    private RefugiDAO(){
    
    }
    
    public static RefugiDAO getInstance(){
        if (inst == null){
            inst = new RefugiDAO();
        }
        return inst;
    }
    
    public boolean isRefugi(String valor) throws SQLException, ClassNotFoundException{
        boolean exists = false;
        try (Connection c = connect(); Statement st = c.createStatement()){
            
            ResultSet rs = st.executeQuery("select nom from refugi where nom='" + valor+"';");
            exists = rs.next();
            rs.close();
        }
        return exists; 
        
    }
    
    private Connection connect() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/wildcare";
        String user = "user";
        return DriverManager.getConnection(url,user,"Asdqwe123");
    }
    
    
    private void deconect(Connection c) throws SQLException{
        c.close(); 
    }
    
}

