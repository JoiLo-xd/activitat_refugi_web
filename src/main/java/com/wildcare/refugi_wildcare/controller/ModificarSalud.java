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

    // Aquest Get basicmanet envia els refugis si hi han sino envia missatge de error, ademes els filtra
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            List<Refugi> refugis = RefugiDAO.getInstance().getRefugis();
            
            if ( refugis.isEmpty()){
                throw new WildCareException("No hi han refugis"); 
            }
            List<Refugi> refugisAmbAnimals = new ArrayList<>();
            for (Refugi r : refugis){
                if (!r.getAnimals().isEmpty()){
                    refugisAmbAnimals.add(r);
                    
                }
                
            }
            request.setAttribute("Refugis", refugisAmbAnimals);
            
            if (refugisAmbAnimals.isEmpty()){
                throw new WildCareException("No hi han Refugis amb animals");
            }
        }catch (SQLException | ClassNotFoundException |WildCareException e){
            request.setAttribute("BigError", e.getMessage());
            
        }
        request.getRequestDispatcher("ModificarSalud.jsp").forward(request, response);
    }
    //Auquest Post el que fa es depen del moment de la finestra envia animals, envia el animal especific, y si hi han altres tambe modifica la salud de l'animal
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try{
            //AQUESTS IFs ELSEs SON els mes importants, depen dels valors que reben llavors faran diferents cosas
            if (request.getParameter("idAnimal") == null && request.getParameter("estat") == null){
                 

                List<Animal> animalsList = RefugiDAO.getInstance().getAnimalsByRefugi(request.getParameter("refugi"));
                request.setAttribute("animals", animalsList);
                request.setAttribute("refugiChoosed",request.getParameter("refugi") );
            
            //Si s'ha enviat la id del animal cambia la intencio del Post
            }else if (request.getParameter("idAnimal") != null){
                String idAnimal = (String) request.getParameter("idAnimal");

                request.setAttribute("Animal", RefugiDAO.getInstance().getAnimal(idAnimal));
                request.setAttribute("refugiChoosed", request.getParameter("refugiEnviado"));
                
                
            }
            //Finalment si trova el estat del animal qu eenvia llavors ja si que cambia el valor
            else if (request.getParameter("estat") != null){
                Animal animal = RefugiDAO.getInstance().getAnimal(request.getParameter("AnimalEscollit"));
                if (animal.getSalud() != EstatSalud.valueOf(request.getParameter("estat"))){
                    animal.setSalud(EstatSalud.valueOf(request.getParameter("estat")));
                    RefugiDAO.getInstance().modifiSaludAnimal(animal);
                    request.setAttribute("FinalMSJ", "Salud Modificada");
                    System.out.println("He llegado aqui");
                }else{
                    throw new WildCareException("No pots posar la mateixa salud que ja tenia abans");
                }
                
            }
        }catch (SQLException | ClassNotFoundException | WildCareException e){
            request.setAttribute("BigError",e.getMessage());
        }
        request.getRequestDispatcher("ModificarSalud.jsp").forward(request,response); 
            
        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
