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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            List<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            if (refugis.isEmpty()){
                throw new WildCareException("No hi han refugis disponibles");
            }
            ArrayList<Refugi> refugisWithSpace = new ArrayList<Refugi>();
            
            for (Refugi r : refugisWithSpace){
                if (r.getAnimals().size() == r.getCapacitat()){
                    throw new WildCareException("No hi han refugis amb llocs per animals");
                }
            }
            
            request.setAttribute("Refugis", refugisWithSpace);
            
                    
            
        } catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("Error",e.getMessage());
        }
        
        
        
            
        
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
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
