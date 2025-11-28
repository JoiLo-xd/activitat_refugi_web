/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.wildcare.refugi_wildcare.controller;

import com.wildcare.refugi_wildcare.enums.TipusRefugi;
import com.wildcare.refugi_wildcare.exception.WildCareException;
import com.wildcare.refugi_wildcare.exception.WildCareExceptionLow;
import com.wildcare.refugi_wildcare.model.Refugi;
import com.wildcare.refugi_wildcare.persistence.RefugiDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
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
@WebServlet(name = "OcupacioPerTipus", urlPatterns = {"/OcupacioPerTipus"})
public class OcupacioPerTipus extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (RefugiDAO.getInstance().getRefugis().isEmpty()) {
                throw new WildCareException("No hi han refguis de cap mena");
            }

        } catch (SQLException | ClassNotFoundException | WildCareException e) {
            request.setAttribute("BigError", e.getMessage());
        }

        request.getRequestDispatcher("OcupacioPerTipus.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("escollitAbans", request.getParameter("tipusEscollit"));
            ArrayList<Refugi> refugis = RefugiDAO.getInstance().getRefugisByTipus(TipusRefugi.valueOf(request.getParameter("tipusEscollit")));
            if (refugis.isEmpty()) {
                throw new WildCareExceptionLow("No hi han refugis del tipo " + request.getParameter("tipusEscollit"));
            } else {
                request.setAttribute("refugis", refugis);
            }

        } catch (SQLException | ClassNotFoundException e) {
            request.setAttribute("BigError", e.getMessage());
        } catch (WildCareExceptionLow e) {
            request.setAttribute("Error", e.getMessage());
        }
        request.getRequestDispatcher("OcupacioPerTipus.jsp").forward(request, response);

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Ocupació Per Tipus d'animal";
    }// </editor-fold>

}
