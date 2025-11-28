<%-- 
    Document   : AnimalsPerSalud
    Created on : Nov 14, 2025, 9:35:09 AM
    Author     : joel_lopez
--%>

<%@page import="com.wildcare.refugi_wildcare.model.Animal"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page import="com.wildcare.refugi_wildcare.enums.EstatSalud"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Animals Per Salut</title>
    </head>
    <body>
        <h1>Animals per salut </h1>
        <%ArrayList<String> estats = new ArrayList<>(List.of("BO", "REGULAR", "GREU"));
            if (request.getAttribute("BigError") != null) {
        %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p><%
        } else if (request.getAttribute("FinalMSJ") != null) {
        %><p><%=request.getAttribute("FinalMSJ")%></p><%
        } else {
        %>
        <form method="POST" action="AnimalsPerSalud">
              <p>Estat de salut:  
                <select name="estat" required>
                    <% for (String e : estats) {
                            if (request.getAttribute("Choosed") != null && EstatSalud.valueOf((String) request.getAttribute("Choosed")) == EstatSalud.valueOf(e)) {
                    %><option selected><%=e%></option><%
                    } else {
                    %><option><%=e%></option><%
                            }

                        } %>
                </select></p>
            <input type="submit" value="Veure animals">


        </form>
        <%if (request.getAttribute("SmallError") == null && request.getAttribute("Choosed") != null) {
        %><h2>=== ANIMALS AMB ESTAT DE SALUD <%=(String) request.getAttribute("Choosed")%> ===</h2><%
            ArrayList<Refugi> refugisFiltrats = (ArrayList<Refugi>) request.getAttribute("refugisFiltrats");
            int sumaAnimals = 0;
            for (Refugi r : refugisFiltrats) {
                sumaAnimals += r.getAnimals().size();
                %> <p>Refugi: <%=r.getNom()%></p>
        <%for (Animal a : r.getAnimals()) {
        %><p>ID <%=a.getId()%> Nom: <%=a.getNom()%></p> <%
            }%>
        <p>-----------------------------------------------------------------------</p>
        <%

            }%><p>Total Animals = <%=sumaAnimals%></p>





        <%
        } else  if (request.getAttribute("SmallError") != null){%>
        <p style="color:red">ERROR: <%=(String) request.getAttribute("SmallError")%></p>



        <%
    }
}%>
    </body>
</html>
