<%-- 
    Document   : OpcupacioPerTipus
    Created on : 28 nov 2025, 15:27:56
    Author     : joel-lopez
--%>

<%@page import="com.wildcare.refugi_wildcare.model.Animal"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page import="com.wildcare.refugi_wildcare.enums.TipusRefugi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ocupació per Tipus</title>
    </head>
    <body>
        <h1>Ocupació per tipus d'animal</h1>
        <%if (request.getAttribute("BigError") != null) {
        %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p>
        <%
        } else {%>
        <form method="POST" action="OcupacioPerTipus"
              <p>Tipus d'Animal:  
                <select name="tipusEscollit" required>
                    <%
                        if (request.getAttribute("escollitAbans") != null) {
                            if (request.getAttribute("escollitAbans").equals("MAMIFERS")) {


                    %><option selected>MAMIFERS</option>
                    <option>OCELLS</option><%                    } else {
                    %>
                    <option selected >OCELLS</option>
                    <option>MAMIFERS</option>
                    <%}
                    } else {
                    %><option>MAMIFERS</option>
                    <option>OCELLS</option><%
                        }

                    %>
                </select> <input type="submit" value="Veure dades"></p>

            <%if (request.getAttribute("escollitAbans") != null) {

                    if (request.getParameter("Error") != null) {
            %><p style="color:red">ERROR: <%=(String) request.getAttribute("Error")%></p><%

            } else {
                ArrayList<Refugi> refugis = (ArrayList<Refugi>) request.getAttribute("refugis");
                int totalRefugis = 0;
                int totalAnimals = 0;
                int totalPlaces = 0;
                for (Refugi r : refugis) {
                    int numAnimals = r.getAnimals().size();
                    totalRefugis += 1;
                    totalAnimals += numAnimals;
                    totalPlaces += (r.getCapacitat() - numAnimals);
                    if (numAnimals != 0) {

                        if (numAnimals != r.getCapacitat()) {
            %>

            <p style='color: blue'>Refugi: <%=r.getNom()%> - Capacitat: <%=r.getCapacitat()%> - Places disponibles: <%= r.getCapacitat() - numAnimals%> </p>
            <%} else {

            %>
            <p style='color: blue'>Refugi: <%=r.getNom()%> - Capacitat: <%=r.getCapacitat()%> - **Està ple** </p>

            <%    }
                for (Animal a : r.getAnimals()) {

            %><p>Codi: <%=a.getId()%> - Nom: <%=a.getNom()%> - Salud: <%=a.getSalud().name()%></p><%

                }%>
            <%} else {
            %>

            <p style='color: blue'>Refugi: <%=r.getNom()%> - Capacitat: <%=r.getCapacitat()%> - **No té Animals** </p>
            <%}
                }
                if (totalPlaces != 0){
            %>
            <h2>Total refugis: <%=totalRefugis%> Total animals: <%=totalAnimals%> -> Queden <%= totalPlaces%> places disponibles per <%=(String) request.getAttribute("escollitAbans")%></h2>



            <%}else{
%>
             <h2>Total refugis: <%=totalRefugis%> Total animals: <%=totalAnimals%> -> No Hi han places disponibles</h2>

            <%
}
                        }
                    }
                }%>
    </body>
</html>
