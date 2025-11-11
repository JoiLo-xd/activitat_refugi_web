/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wildcare.refugi_wildcare.model;

import com.wildcare.refugi_wildcare.enums.EstatSalud;
import com.wildcare.refugi_wildcare.enums.TipusAnimal;

/**
 *
 * @author joellopez
 */
public class Animal {
    
    private int id; 
    private String nom;
    private TipusAnimal tipus;
    private int anyIngress;
    private EstatSalud salud;
    private boolean bebe;

    public Animal(int id, String nom, TipusAnimal tipus, int anyIngress, EstatSalud salud, boolean bebe) {
        this.id = id;
        this.nom = nom;
        this.tipus = tipus;
        this.anyIngress = anyIngress;
        this.salud = salud;
        this.bebe = bebe;
    }
    public Animal(String nom, TipusAnimal tipus, int anyIngress, EstatSalud salud, boolean bebe) {
        this.nom = nom;
        this.tipus = tipus;
        this.anyIngress = anyIngress;
        this.salud = salud;
        this.bebe = bebe;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public TipusAnimal getTipus() {
        return tipus;
    }

    public int getAnyIngress() {
        return anyIngress;
    }

    public EstatSalud getSalud() {
        return salud;
    }

    public boolean isBebe() {
        return bebe;
    }

    
    
}
