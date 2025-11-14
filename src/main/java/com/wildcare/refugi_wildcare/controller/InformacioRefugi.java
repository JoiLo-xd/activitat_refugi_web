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
@WebServlet(name = "InformacioRefugi", urlPatterns = {"/InformacioRefugi"})
public class InformacioRefugi extends HttpServlet {

    


    // aquest do get envia els refugis si hi han sino missatge de error, no es filtren.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            if (refugis.isEmpty()){
                throw new WildCareException("No hi ha refugis registrats.");
            }
            
            request.setAttribute("Refugis", refugis);
        } catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("BigError", e.getMessage());
        }
        request.getRequestDispatcher("InformacioRefugi.jsp").forward(request, response);
                
    }

   
    // Aquest Post nomes indica el refugi especific
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("refugi", RefugiDAO.getInstance().getRefugiByName(request.getParameter("refugi")));
            
        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("BigError", e.getMessage());
        } 
        request.getRequestDispatcher("InformacioRefugi.jsp").forward(request, response);
        
    }

    
    @Override
    public String getServletInfo() {
        return "Short informacio de refugi";
    }// </editor-fold>

}
