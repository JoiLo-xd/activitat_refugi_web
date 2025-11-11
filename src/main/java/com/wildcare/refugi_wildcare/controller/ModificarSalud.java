/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.model.Refugi;
import com.wildcare.refugi_wildcare.persistence.RefugiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel-lopez
 */
@WebServlet(name = "ModificarSalud", urlPatterns = {"/ModificarSalud"})
public class ModificarSalud extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            List<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            for (Refugi r : refugis){
                if (r.getAnimals().isEmpty()){
                    
                }
                
            }
        }catch (SQLException | ClassNotFoundException e){
            
            
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
