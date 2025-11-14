/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.wildcare.refugi_wildcare.model;

import com.wildcare.refugi_wildcare.enums.TipusRefugi;

/**
 *
 * @author joel_lopez
 */
public class StatsTO {
    private String nomRefugi;
    private TipusRefugi tipusRefugi;
    private int placesDispo;
    private int capacitat;
    private int animalsActuals;
    private int animalsBons;
    private int animalsRegulars;
    private int animalsGreus;

    public StatsTO(String nomRefugi, TipusRefugi tipusRefugi, int placesDispo, int capacitat, int animalsActuals) {
        this.nomRefugi = nomRefugi;
        this.tipusRefugi = tipusRefugi;
        this.placesDispo = placesDispo;
        this.capacitat = capacitat;
        this.animalsActuals = animalsActuals;
        
    }

    public String getNomRefugi() {
        return nomRefugi;
    }

    public void setNomRefugi(String nomRefugi) {
        this.nomRefugi = nomRefugi;
    }

    public TipusRefugi getTipusRefugi() {
        return tipusRefugi;
    }

    public void setTipusRefugi(TipusRefugi tipusRefugi) {
        this.tipusRefugi = tipusRefugi;
    }

    public int getPlacesDispo() {
        return placesDispo;
    }

    public void setPlacesDispo(int placesDispo) {
        this.placesDispo = placesDispo;
    }

    public int getCapacitat() {
        return capacitat;
    }

    public void setCapacitat(int capacitat) {
        this.capacitat = capacitat;
    }

    public int getAnimalsActuals() {
        return animalsActuals;
    }

    public void setAnimalsActuals(int animalsActuals) {
        this.animalsActuals = animalsActuals;
    }

    public int getAnimalsBons() {
        return animalsBons;
    }

    public void setAnimalsBons(int animalsBons) {
        this.animalsBons = animalsBons;
    }

    public int getAnimalsRegulars() {
        return animalsRegulars;
    }

    public void setAnimalsRegulars(int animalsRegulars) {
        this.animalsRegulars = animalsRegulars;
    }

    public int getAnimalsGreus() {
        return animalsGreus;
    }

    public void setAnimalsGreus(int animalsGreus) {
        this.animalsGreus = animalsGreus;
    }

    
    
    
}
