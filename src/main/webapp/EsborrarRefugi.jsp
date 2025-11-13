<%-- 
    Document   : EsborrarRefugi
    Created on : 13 nov 2025, 15:03:46
    Author     : joel-lopez
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="com.wildcare.refugi_wildcare.model.Refugi"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Esborrar Refugi</title>
    </head>
    <body>
        <h1>Esborrar Refugi</h1>
        <form action="EsborrarRefugi" method="POST">
            <%if (request.getAttribute("BigError") != null) {
            %><p style="color:red">ERROR: <%=(String) request.getAttribute("BigError")%></p><%
            } else if (request.getAttribute("FinalMSJ") != null) {
            %><p><%=request.getAttribute("FinalMSJ")%></p><%
            } else {%>
            <form action="EsborrarRefugi" method="POST">

                <p>Escull un refugi per esborrar: 
                    <select name="refugi" required>
                        <%for (Refugi r : (ArrayList<Refugi>) request.getAttribute("Refugis")) {
                        %>
                        <option><%= r.getNom()%></option>
                        <%

                            }
                        %>
                    </select>
                    <input type="submit" value="Esborrar Refugi">
                </p>
                </from>
                <%}%>

</body>
</html>
