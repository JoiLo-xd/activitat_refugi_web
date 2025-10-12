/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.persistence.RefugiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joellopez
 */
@WebServlet(name = "AfegirRefugi", urlPatterns = {"/AfegirRefugi"})
public class AfegirRefugi extends HttpServlet {

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            if (RefugiDAO.getInstance().isRefugi((String) request.getAttribute("nom"))){
                
                throw new WildCareException("Ja existeix un recinte amb el nom indicat.");
            
            }
            else{
                
                
            }
        
        
        }catch (SQLException | ClassNotFoundException | WildCareException e){
            request.setAttribute("Error", e.getMessage());
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Afegir Refugi Servlet";
    }// </editor-fold>

}
