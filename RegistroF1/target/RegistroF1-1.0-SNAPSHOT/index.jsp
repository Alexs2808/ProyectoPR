<%-- 
    Document   : index
    Created on : 8 dic 2025, 21:50:47
    Author     : Alexs
GET -- OBTENER INFORMACION
POST -- ENVIAR/MODIFICAR DE ALTAS EN BD
PUT -- MODIFICACIONES
DELETE -- ELIMINACIONES
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro de Equipos</title>
    </head>
    <body>
        <h1>Datos del Equipo</h1>
        <form action="SvUsuarios" method="POST">
            <p><label>Nombre del equipo:</label><input type="text" name="NombreEquipo"></p>
            <p><label>Nombre de usuario:</label><input type="text" name="NombreUsuario"></p>
            <p><label>Contrasenia:</label><input type="text" name="Contrasenia"></p>
            <button type="submit">Registrar</button>
        </form>
        
        <h1>Ver lista de equipos</h1>
        <p>Ver informacion:</p>
        <form action="SvUsuarios" method="GET">
            <button type="submit">Mostrar</button>
            
        </form>
        
    </body>
</html>
