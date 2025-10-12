/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wildcare.refugi_wildcare.model;

import com.wildcare.refugi_wildcare.enums.TipusRefugi;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joellopez
 */
public class Refugi {
    
    String nom;
    TipusRefugi tipus;
    int capacitat;
    List<Animal> animals;
    
    public Refugi(String nom, TipusRefugi tipus, int capacitat ){
        this.nom = nom;
        this.tipus = tipus;
        this.capacitat = capacitat;
        animals = new ArrayList<>(); 
    }
    
    
    
}
