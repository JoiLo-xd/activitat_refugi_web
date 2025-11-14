/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.enums.EstatSalud;
import com.wildcare.refugi_wildcare.enums.TipusAnimal;
import com.wildcare.refugi_wildcare.enums.TipusRefugi;
import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.model.Animal;
import com.wildcare.refugi_wildcare.model.Refugi;
import com.wildcare.refugi_wildcare.persistence.RefugiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel-lopez
 */
@WebServlet(name = "AfegirAnimal", urlPatterns = {"/AfegirAnimal"})
public class AfegirAnimal extends HttpServlet {
    
    
    /*
    APUNTE RAPIDO:
    getParametre() = Esto es cunado queremos obtener datos que ha enviado el cliente a 
    traves de un formulario al servidor y desde el servidor o obtenemos.
    
    getAttribute() = Cuando el cliente necessita algun valor que hemos puesto desde el servlet
    
    
    
    
    */
    
    //Este get primer revisa que hi hagin refugis, despres envia tots el refugis que tinguin espai, si no te tambe surt un missatge de error 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            if (refugis.isEmpty()){
                throw new WildCareException("No hi han refugis disponibles");
            }
            ArrayList<Refugi> refugisWithSpace = new ArrayList<Refugi>();
            
            for (Refugi r : refugis){
                if (r.getAnimals().size() != r.getCapacitat()){
                    refugisWithSpace.add(r); 
                }
                
            }
            if(refugisWithSpace.isEmpty()){
                throw new WildCareException("No hi han refugis amb espai disponibles");
            }else{
            
                request.setAttribute("Refugis", refugisWithSpace);
            }
                    
            
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("Error",e.getMessage());
        }
        catch (WildCareException e){
            request.setAttribute("BigError", e.getMessage());
        }
        
        request.getRequestDispatcher("AfegirAnimal.jsp").forward(request, response);
         
    }
    

    // Este Post basicamente añade el Animal en el Refugio indicado
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            
            String nom = request.getParameter("nom");
            TipusAnimal tipus = TipusAnimal.valueOf(request.getParameter("tipus"));
            int anyIngress = Integer.parseInt(request.getParameter("anyIngress"));
            EstatSalud salud = EstatSalud.valueOf(request.getParameter("estat"));
            boolean bebe = request.getAttribute("bebe") != null;
            String nomRefugi = request.getParameter("refugi");
            
            if ((RefugiDAO.getInstance().getRefugiByName(nomRefugi).getTipus() == TipusRefugi.MAMIFERS && tipus == TipusAnimal.MAMIFER) ||
                    RefugiDAO.getInstance().getRefugiByName(nomRefugi).getTipus() == TipusRefugi.OCELLS && tipus == TipusAnimal.OCELL){
                    Animal animal = new Animal(nom,tipus,anyIngress,salud,bebe);
                    RefugiDAO.getInstance().addAnimal(animal, nomRefugi);
                    request.setAttribute("msg", "Animal Registrat Correctament");     
            }else{
                throw new WildCareException("EL tipus del Animal no concorda amb el tipus del refugi");
            }
     
            
            
            
        }catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("Error",e.getMessage());
        
        
        }
        
        doGet(request,response); 
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Afegir Animal servlet";
    }// </editor-fold>

}
