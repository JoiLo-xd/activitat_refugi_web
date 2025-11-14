/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.model.StatsTO;
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
 * @author joel_lopez
 */
@WebServlet(name = "Stats", urlPatterns = {"/Stats"})
public class Stats extends HttpServlet {

    
    //Aquest get el que fa es mirar si hi han refugis, despres envia el reguis, No Hi ha post perque nomes enseña estadistiques, si no hi han refugis envia un missatge de error. 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<StatsTO> stats = RefugiDAO.getInstance().getStats();
            if (stats.isEmpty()){
                throw new WildCareException("No hi han refugis per mostrar estadistiques");
            }
            
            request.setAttribute("stats", stats);
        } catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("BigError", e.getMessage());
        }
        request.getRequestDispatcher("Stats.jsp").forward(request, response);

        
    }





    @Override
    public String getServletInfo() {
        return "Servlet de Stats";
    }// </editor-fold>

}
