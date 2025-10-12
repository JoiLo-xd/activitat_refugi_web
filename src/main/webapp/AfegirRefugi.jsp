<%-- 
    Document   : AfegirRefugi
    Created on : 12 oct 2025, 14:10:31
    Author     : joellopez
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Afegir Refugi</title>
    </head>
    <body>
        <h1>Afegir Refugi</h1>
        <form action="AfegirRefugi" method="POST">
            <p>Nom: <input type="text" name="nom" required></p>
            <p>Tipus Animal: 
            <select name="tipus" required>
                <option>MAMIFERS</option>
                <option>OCELLS</option>
            </select>
            </p>
            <p>Capacitat: <input type="text" name="capacitat" required></p>
            <p><input type="submit" name="Add" value="Afegir"></p>
        </form>
    </body>
</html>
