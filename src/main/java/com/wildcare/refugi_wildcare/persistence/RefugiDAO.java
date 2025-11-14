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
import com.wildcare.refugi_wildcare.model.StatsTO;
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

    private RefugiDAO() {

    }

    public static RefugiDAO getInstance() {
        if (inst == null) {
            inst = new RefugiDAO();
        }
        return inst;
    }

    public ArrayList<Refugi> getRefugis() throws SQLException, ClassNotFoundException {
        try (Connection c = connect(); Statement st = c.createStatement()) {
            ArrayList<Refugi> refugis = new ArrayList<Refugi>();
            ResultSet rs = st.executeQuery("select * from refugi");
            while (rs.next()) {
                String nom = rs.getString(1);
                TipusRefugi tipus = TipusRefugi.valueOf(rs.getString(2));
                int capacitat = rs.getInt(3);
                Refugi refugi = new Refugi(nom, tipus, capacitat);
                refugi.putAllAnimals(getAnimalsByRefugi(nom));
                refugis.add(refugi);
            }
            return refugis;
        }

    }
    
    public List<Animal> getAnimalsBySalud(EstatSalud salud) throws SQLException, ClassNotFoundException{
        try (Connection c = connect(); Statement st = c.createStatement()) {
            ArrayList<Animal> animals = new ArrayList<Animal>();
            ResultSet rs = st.executeQuery("select * from animal where salut='" + salud.name() + "';");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                TipusAnimal tipus = TipusAnimal.valueOf(rs.getString(3));
                int anyIngres = rs.getInt(4);
                EstatSalud estat = EstatSalud.valueOf(rs.getString(5));
                boolean bebe = rs.getBoolean(6);
                animals.add(new Animal(id, nom, tipus, anyIngres, estat, bebe));
            }
            return animals;
        }
    }

    public void deleteRefugi(String nom) throws SQLException, ClassNotFoundException {
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement("delete from refugi where nom=?;")) {
            ps.setString(1, nom);
            ps.executeUpdate();
        }
    }

    public List<Animal> getAnimalsByRefugi(String nameRefugi) throws SQLException, ClassNotFoundException {
        try (Connection c = connect(); Statement st = c.createStatement()) {
            ArrayList<Animal> animals = new ArrayList<Animal>();
            ResultSet rs = st.executeQuery("select * from animal where refugi='" + nameRefugi + "';");
            while (rs.next()) {
                int id = rs.getInt(1);
                String nom = rs.getString(2);
                TipusAnimal tipus = TipusAnimal.valueOf(rs.getString(3));
                int anyIngres = rs.getInt(4);
                EstatSalud estat = EstatSalud.valueOf(rs.getString(5));
                boolean bebe = rs.getBoolean(6);
                animals.add(new Animal(id, nom, tipus, anyIngres, estat, bebe));
            }
            return animals;
        }
    }

    public Animal getAnimal(String idAnimal) throws SQLException, ClassNotFoundException {
        try (Connection c = connect(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery("select *from animal where idanimal='" + idAnimal + "';");
            rs.next();
            int id = rs.getInt(1);
            String nom = rs.getString(2);
            TipusAnimal tipus = TipusAnimal.valueOf(rs.getString(3));
            int anyIngres = rs.getInt(4);
            EstatSalud estat = EstatSalud.valueOf(rs.getString(5));
            boolean bebe = rs.getBoolean(6);
            return new Animal(id, nom, tipus, anyIngres, estat, bebe);

        }
    }

    public void modifiSaludAnimal(Animal animal) throws SQLException, ClassNotFoundException {
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement("update animal set salut=? where idAnimal=?")) {

            ps.setString(1, animal.getSalud().name());
            ps.setInt(2, animal.getId());
            ps.executeUpdate();
        }
    }

    public void addRefugi(Refugi refugi) throws SQLException, ClassNotFoundException {

        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement("insert into refugi values (?,?,?);")) {
            ps.setString(1, refugi.getNom());
            ps.setString(2, refugi.getTipus().name());
            ps.setInt(3, refugi.getCapacitat());
            ps.executeUpdate();
        }

    }

    public Refugi getRefugiByName(String nomRefugi) throws SQLException, ClassNotFoundException {

        try (Connection c = connect(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery("select * from refugi where nom='" + nomRefugi + "';");
            rs.next();
            String nom = rs.getString(1);
            TipusRefugi tipus = TipusRefugi.valueOf(rs.getString(2));
            int max = rs.getInt(3);
            Refugi r = new Refugi(nom, tipus, max);
            r.putAllAnimals(getAnimalsByRefugi(nomRefugi));
            return r;
        }

    }

    public void addAnimal(Animal animal, String nomRefugi) throws SQLException, ClassNotFoundException {
        //insert into animal values(null,"animal,pruebabd","OCELL",2021,"BO",false,"prova1_real")
        try (Connection c = connect(); PreparedStatement ps = c.prepareStatement("insert into animal values (null,?,?,?,?,?,?);")) {
            ps.setString(1, animal.getNom());
            ps.setString(2, animal.getTipus().name());
            ps.setInt(3, animal.getAnyIngress());
            ps.setString(4, animal.getSalud().name());
            ps.setBoolean(5, animal.isBebe());
            ps.setString(6, nomRefugi);
            ps.executeUpdate();
        }
    }

    public boolean isRefugi(String valor) throws SQLException, ClassNotFoundException {
        boolean exists = false;
        try (Connection c = connect(); Statement st = c.createStatement()) {

            ResultSet rs = st.executeQuery("select nom from refugi where nom='" + valor + "';");
            exists = rs.next();
            rs.close();
        }
        return exists;

    }
    
    
    public ArrayList<StatsTO> getStats() throws SQLException, ClassNotFoundException{
        try (Connection c = connect(); Statement st = c.createStatement()) {
            ResultSet rs = st.executeQuery("select r.nom, r.capacitat, r.tipus_animal, count(a.idanimal) as \"animals_actuals\" from refugi as r left join animal as a on a.refugi = r.nom GROUP BY r.nom, r.capacitat, r.tipus_animal;");
            ArrayList<StatsTO> statsAll = new ArrayList<>();
            while (rs.next()){
                String nom = rs.getString(1);
                int capacitat = rs.getInt(2);
                TipusRefugi tipus = TipusRefugi.valueOf(rs.getString(3));
                int animalsActuals = rs.getInt(4);
                int placesDispo = capacitat - animalsActuals; 
                statsAll.add(new StatsTO(nom, tipus, placesDispo, capacitat, animalsActuals));
                int bons = getAnimalPerSalud(nom, "BO", c);
                int regulars = getAnimalPerSalud(nom, "REGULAR", c);
                int greus = getAnimalPerSalud(nom, "GREU", c);
                statsAll.getLast().setAnimalsBons(bons);
                statsAll.getLast().setAnimalsGreus(greus);
                statsAll.getLast().setAnimalsRegulars(regulars);         
            }
            return statsAll;  
            
            
            
        } 
    }
    
    public int getAnimalPerSalud(String re, String sa, Connection c) throws SQLException, ClassNotFoundException{
        Statement st = c.createStatement();
        ResultSet rs = st.executeQuery("select count(*) from animal where refugi='" +re+ "' and salut='" + sa + "';");
        rs.next();
        int total = rs.getInt(1);
        return total;
    }
    

    private Connection connect() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/wildcare";
        String user = "user_root";
        return DriverManager.getConnection(url, user, "Asdqwe123");
    }
    
    
    
    
    

    private void deconect(Connection c) throws SQLException {
        c.close();
    }

}
