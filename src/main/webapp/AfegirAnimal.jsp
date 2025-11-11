<%-- 
    Document   : AfegirAnimal
    Created on : 9 nov 2025, 20:18:04
    Author     : joel-lopez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afegir Animal</title>
    </head>
    <body>
        <h1>Afegir Animal</h1>
        <% if (request.getAttribute("Error") == null) {%>
        <form action="AfegirAnimal" method="POST">
            <p>Nom: <input type="text" name="nom" required></p>
            <p>Tipus Animal: 
                <select name="tipus" required>
                    <option>MAMIFER</option>
                    <option>OCELL</option>
                </select>
            </p>
            <p>Any Ingres: <input type="number" min="1944" name="anyIngress" required></p>
            <p>Estat de salud:  
                <select name="estat" required>
                    <option>BO</option>
                    <option>REGULAR</option>
                    <option>GREU</option>
                </select>
            </p>
            <p>És bebe? <input type="checkbox" name="bebe"></p>

            <p>Refugi:  
                <select name="refugi" required>
                    <%for (Refugi r : (ArrayList<Refugi>) request.getAttribute("Refugis")){
                        %>
                        <option><%= r.getNom()%></option>
                        <%
                        
                    }
                    %>
                </select>
            </p>

            <p><input type="submit" name="Add" value="Afegir"></p>
                <%}%>

        </form>

        <%if (request.getAttribute("Error") != null) {
        %>
        <p style="color:red">ERROR: <%=(String) request.getAttribute("Error")%></p>
        <%
        } else if (request.getAttribute("msg") != null) {
        %><p><%= (String) request.getAttribute("msg")%></p>             <%
            }
        %>

    </body>
</html>
