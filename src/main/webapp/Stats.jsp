<%-- 
    Document   : Stats
    Created on : Nov 14, 2025, 11:47:31 AM
    Author     : joel_lopez
--%>

<%@page import="com.wildcare.refugi_wildcare.model.StatsTO"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Estadístiques</title>
    </head>
    <body>
        <h1>Estadístiques</h1>
        <%if (request.getAttribute("BigError") != null) {
        %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p>
            <%
            } else {%> <table> <%
                    int total = 0;
                    for (StatsTO to : (ArrayList<StatsTO>) request.getAttribute("stats")) {
                        total += to.getAnimalsActuals();
            %>
            <tr>
                <td style="font-weight: bold;"> Nom refugi: <%=to.getNomRefugi()%></td>
                <td>Animals que pot acollit: <%=to.getTipusRefugi()%></td>
               
                <!-- Aquesta Ternaria si es positiva ho tira a un altre Ternaria ( ES UNA TERNARIA DOBLE!!!) :) -->
                <td><%=(to.getPlacesDispo()>0)? ((to.getPlacesDispo() == to.getCapacitat())? "--Actualment no te animals --" : "Places disponibles: " + to.getPlacesDispo()) : "--Refugi PLE --"%></td>
                
            </tr>
            <%if (!(to.getPlacesDispo() == to.getCapacitat())){
                %>
                <tr>
                <td>Capacitat: <%=to.getCapacitat() %> </td>
                <td>Animals Actuals:  <%=to.getAnimalsActuals() %> </td>
                </tr>
                <tr>
                <td>BO: <%=to.getAnimalsBons() %></td>
                <td>REGULAR: <%=to.getAnimalsRegulars()%></td>
                <td>GREU: <%=to.getAnimalsGreus()%></td>
                </tr>

            <%
            }%>
            
            <%
            }
            %></table>
            <p style="font-weight: bold;">Total Animals <%= total %> </p>
            <%
            }
            %>

    </body>
</html>
