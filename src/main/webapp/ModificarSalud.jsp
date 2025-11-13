<%-- 
    Document   : ModificarSalud
    Created on : 12 nov 2025, 11:50:48
    Author     : joel-lopez
--%>

<%@page import="com.wildcare.refugi_wildcare.enums.EstatSalud"%>
<%@page import="com.wildcare.refugi_wildcare.model.Animal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modificar Salud</title>
    </head>
    <body>
        <h1>Modificar Salud</h1>
        <%if (request.getAttribute("BigError") != null) {
        %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p><%
        } else if (request.getAttribute("FinalMSJ") != null) {
        %><p><%=request.getAttribute("FinalMSJ")%></p><%
        } else {
            if (request.getAttribute("animals") == null && request.getAttribute("Animal") == null) {
        %>
        <form action="ModificarSalud" method="POST">
            <p>Escull el refugi per veureel llistat d'animals:
                <select name="refugi" required>
                    <%for (Refugi r : (ArrayList<Refugi>) request.getAttribute("Refugis")) {
                    %>
                    <option><%= r.getNom()%></option>
                    <%

                        }
                    %>
                </select></p>
            <p><input type="submit" name="GetTheRefugi" value="Mostrar Animals"></p>

            <%} else if (request.getAttribute("animals") != null) {
            %>
            <h2>Animals del Refugi: <%= request.getAttribute("refugiChoosed")%></h2>

            <table>
                <tr>
                    <th>ID Animal</th>
                    <th>Nom</th>
                    <th>Estat de Salud</th>
                    <th></th>
                </tr>

                <% for (Animal a : (ArrayList<Animal>) request.getAttribute("animals")) {
                %>
                <tr>
                    <td><%=a.getId()%></td>
                    <td><%=a.getNom()%></td>
                    <td><%=a.getSalud().name()%></td>
                    <td>
                        <form action="ModificarSalud" method="POST">
                            <input type="hidden" name="idAnimal" value="<%=a.getId()%>">
                            <input type="hidden" name="refugiEnviado" value="<%=a.getId()%>">
                            <input type="submit" value="Modificar Salud">

                        </form>
                    </td>
                    <%}%>

            </table>

            <%  } else if ((Animal) request.getAttribute("Animal") != null) {
                Animal animal = (Animal) request.getAttribute("Animal");

            %>
            <h2><%=animal.getId()%> - <%=animal.getNom()%></h2>
            <form action="ModificarSalud" method="POST">
                <p>Estat de Salud Actual: <%=animal.getSalud().name()%></p>
                <form >
                    <p>Estat de salud:  
                        <select name="estat" required>
                            <option>BO</option>
                            <option>REGULAR</option>
                            <option>GREU</option>
                        </select>
                    </p>
                    <input type="hidden" name="AnimalEscollit" value="<%=animal.getId()%>"> 
                    <input type="submit" value="Modificar Salud">

                </form>
                <% }

                    }
                %>
                </body>
                </html>
