<%-- 
    Document   : ModificarSalud
    Created on : 12 nov 2025, 11:50:48
    Author     : joel-lopez
--%>

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
            }else{
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
             
         <%}%>
    </body>
</html>
