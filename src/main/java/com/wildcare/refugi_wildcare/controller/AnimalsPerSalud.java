/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.enums.EstatSalud;
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
 * @author joel_lopez
 */
@WebServlet(name = "AnimalsPerSalud", urlPatterns = {"/AnimalsPerSalud"})
public class AnimalsPerSalud extends HttpServlet {

    

    // Aquest Get el que fa es primer revisar que hi hagin refugi ,y despres que envia el request
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            if (refugis.isEmpty()){
                throw new WildCareException("NO hi han refugis, aixi que no hi han animals");
            }
            
            
        } catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("BigError", e.getMessage());
        }
        
        request.getRequestDispatcher("AnimalsPerSalud.jsp").forward(request, response);
    }

    //Aquest doPOst el que a es primer agafa els refugis, els filtra, tambe envia 2 tipos de errors ja que un es global y un altr es mes normal
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            ArrayList<Refugi> refugisAmbAnimalsEstat = new ArrayList<>();
            List<Animal> animalsFiltrats;
            EstatSalud escollit = EstatSalud.valueOf(request.getParameter("estat"));
            
            for (Refugi r : refugis) {
                animalsFiltrats = new ArrayList<>(); 
                
                for (Animal a : r.getAnimals()){
                    if (a.getSalud() == escollit){
                        animalsFiltrats.add(a);
                    }
                }
                
                if (!animalsFiltrats.isEmpty()){
                    r.putAllAnimals(animalsFiltrats);
                    refugisAmbAnimalsEstat.add(r);
                }
            } 
            if (refugisAmbAnimalsEstat.isEmpty()){
                throw new WildCareException("No hi han animals amb el Estat: " + escollit.name());
            }
            request.setAttribute("refugisFiltrats", refugisAmbAnimalsEstat);
            request.setAttribute("Choosed", request.getParameter("estat"));
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("BigError", e.getMessage());
        } catch(WildCareException e){
            request.setAttribute("SmallError", e.getMessage());
            request.setAttribute("Choosed", request.getParameter("estat"));
        }
        
        request.getRequestDispatcher("AnimalsPerSalud.jsp").forward(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Animals per Salud Servlet";
    }// </editor-fold>

}
