<%-- 
    Document   : InformacioRefugi
    Created on : 13 nov 2025, 17:54:10
    Author     : joel-lopez
--%>

<%@page import="com.wildcare.refugi_wildcare.model.Animal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informacio Refugi</title>
    </head>
    <body>
        <h1>Informació del Refugi</h1>

        <%if (request.getAttribute("BigError") != null) {
        %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p><%
            } else if (request.getAttribute("FinalMSJ") != null) {
        %><p><%=request.getAttribute("FinalMSJ")%></p><%
        } else {
            if (request.getAttribute("refugi") == null) {%>
        <form action="InformacioRefugi" method="POST">

            <p>Escull un refugi per esborrar: 
                <select name="refugi" required>
                    <%for (Refugi r : (ArrayList<Refugi>) request.getAttribute("Refugis")) {
                    %>
                    <option><%= r.getNom()%></option>
                    <%

                        }
                    %>
                </select>
                <input type="submit" value="Informació Refugi">
            </p>
        </from>
        <%} else {
            Refugi refu = (Refugi) request.getAttribute("refugi");
        %>
        <p>Nom del refugi: <%=refu.getNom()%></p>
        <p>Tipus d'animal que pot tenir: <%=refu.getTipus().name()%></p>
        <%if (!refu.getAnimals().isEmpty()) {%>
        <p>=== ANIMALS DEL RECINTE === <br/></p>
            <%for (Animal a : refu.getAnimals()) {
            %><p>ID: <%=a.getId()%> NOM: <%=a.getNom()%> Any:<%=a.getAnyIngress()%> Estat de Salud: <%=a.getSalud().name()%> Bebé: <%=a.isBebe() ? "SI" : "NO"%> </p>

        <%
                    }%><p> ==> Capacitat maxima: <%=refu.getCapacitat()%> Animals <%= refu.getAnimals().size()%> Capacitat Actual: <%=refu.getAnimals().size() - refu.getCapacitat()%></p><%


        %><% } else {
        %><p>==> No hi han animals registrats <==</p>
        <p>Capacitat Maxima <%=refu.getCapacitat()%></p>
        <%
                    }
                }
            }
        %>

</body>
</html>
