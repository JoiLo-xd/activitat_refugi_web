/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.enums.TipusRefugi;
import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.model.Refugi;
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
    
    

    // Nomes aquest Post afeixieix un refugi si no esta.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            
            if (RefugiDAO.getInstance().isRefugi((String) request.getParameter("nom"))){
                
                throw new WildCareException("Ja existeix un recinte amb el nom indicat.");
            
            }
            
            else{
                Refugi refugi = new Refugi(request.getParameter("nom"),TipusRefugi.valueOf(request.getParameter("tipus")),Integer.parseInt(request.getParameter("capacitat")));               
                RefugiDAO.getInstance().addRefugi(refugi);
                request.setAttribute("msg","Refugi registrat correctament." );
            }
        
        
        }catch (SQLException | ClassNotFoundException | WildCareException e){
            request.setAttribute("Error", e.getMessage());
        }catch (NumberFormatException e){ //No es dona pero per si decas jeje
            request.setAttribute("Error", "La capacitat ha de ser numerica");
        }
        
        request.getRequestDispatcher("/AfegirRefugi.jsp").forward(request, response);
        
        
        
        
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
