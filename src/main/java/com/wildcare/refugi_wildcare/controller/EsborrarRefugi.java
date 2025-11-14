/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.model.Refugi;
import com.wildcare.refugi_wildcare.persistence.RefugiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "EsborrarRefugi", urlPatterns = {"/EsborrarRefugi"})
public class EsborrarRefugi extends HttpServlet {

    //En aquest Get el que fa es mirar que hi hagin refugis, despres els filtra, i els envia filtrats
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            ArrayList<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            if (refugis.isEmpty()){
                throw new WildCareException("No tens refugis per esborrar");
            }
            ArrayList<Refugi> refugisSenseAnimals = new ArrayList<>();
            for (Refugi r : refugis){
                if (r.getAnimals().isEmpty()){
                    refugisSenseAnimals.add(r);
                }
            }
            if (refugisSenseAnimals.isEmpty()){
                throw new WildCareException("No tens refugis sense animals");
            }
            
            request.setAttribute("Refugis", refugisSenseAnimals);
            
        }catch (WildCareException | SQLException | ClassNotFoundException e){
            
            request.setAttribute("BigError", e.getMessage());
        }
        
        request.getRequestDispatcher("EsborrarRefugi.jsp").forward(request,response); 
        
        
    }
    // El post aquest el que fa es informar de si s'ha borrat el refugi.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       try{
           RefugiDAO.getInstance().deleteRefugi(request.getParameter("refugi"));
           request.setAttribute("FinalMSJ", "S'ha esborrat el Refugi");
       }catch (SQLException | ClassNotFoundException e){
            
            request.setAttribute("BigError", e.getMessage());
        }
       
       request.getRequestDispatcher("EsborrarRefugi.jsp").forward(request,response); 

    }


    @Override
    public String getServletInfo() {
        return "Esborrar Refugi";
    }// </editor-fold>

}
