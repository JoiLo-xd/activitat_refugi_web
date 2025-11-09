/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wildcare.refugi_wildcare.persistence;

import com.wildcare.refugi_wildcare.enums.EstatSalud;
import com.wildcare.refugi_wildcare.enums.TipusAnimal;
import com.wildcare.refugi_wildcare.enums.TipusRefugi;
import com.wildcare.refugi_wildcare.model.Animal;
import com.wildcare.refugi_wildcare.model.Refugi;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public ArrayList<Refugi> getRefugis() throws SQLException, ClassNotFoundException{
        try (Connection c = connect(); Statement st = c.createStatement()){
            ArrayList<Refugi> refugis = new ArrayList<Refugi>();
            ResultSet rs = st.executeQuery("select * from refugi");
            while (rs.next()){
                String nom = rs.getString(1);
                TipusRefugi tipus = TipusRefugi.valueOf(rs.getString(2));
                int capacitat = rs.getInt(3);
                Refugi refugi = new Refugi(nom, tipus, capacitat);
                refugi.putAllAnimals(getAnimalsByRefugi(nom));
            }
            return refugis;
        }
        
    }
    
    
    public List<Animal> getAnimalsByRefugi(String nameRefugi) throws SQLException, ClassNotFoundException{
        try (Connection c = connect(); Statement st = c.createStatement()){
            ArrayList<Animal> animals = new ArrayList<Animal>();
            ResultSet rs = st.executeQuery("select * from animal where refugi =" + nameRefugi + ";");
            while (rs.next()){
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                TipusAnimal tipus = TipusAnimal.valueOf(rs.getString(3));
                int anyIngres = rs.getInt(4);
                EstatSalud estat = EstatSalud.valueOf(rs.getString(5));
                boolean bebe = rs.getBoolean(6);
                animals.add(new Animal(id,nom,tipus,anyIngres,estat,bebe));
            }
            return animals;
        }
    }
    
    
    
    public void addRefugi(Refugi refugi) throws SQLException, ClassNotFoundException{
        
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement("insert into refugi values (?,?,?);") ){
            ps.setString(1, refugi.getNom());
            ps.setString(2, refugi.getTipus().name());
            ps.setInt(3, refugi.getCapacitat());
            ps.executeUpdate();
        }
        
        
    }
    
    
    public boolean isRefugi(String valor) throws SQLException, ClassNotFoundException{
        boolean exists = false;
        try (Connection c = connect(); Statement st = c.createStatement()){
            
            ResultSet rs = st.executeQuery("select nom from refugi where nom='" +valor+"';");
            exists = rs.next();
            rs.close();
        }
        return exists; 
        
    }
    
    private Connection connect() throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/wildcare";
        String user = "user_root";
        return DriverManager.getConnection(url,user,"Asdqwe123");
    }
    
    
    private void deconect(Connection c) throws SQLException{
        c.close(); 
    }
    
}

