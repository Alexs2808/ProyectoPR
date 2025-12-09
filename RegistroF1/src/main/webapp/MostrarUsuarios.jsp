<%@page import="java.util.List"%>
<%@page import="logica.Usuarios"%>
<%@page import="logica.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prueba de mostrar usuarios</title>
    </head>
    <body>
        <h1>Lista de registro</h1>
        <%
            List<Usuarios>listaUsuarios = (List) request.getSession().getAttribute("listaUsuarios");
            int cont = 1;
            for(Usuarios usu : listaUsuarios){
            
        %>
        
        <p><b>Lista de Usuarios no. <%=cont%></b></p>
        <p>Equipo <%=usu.getNombreEquipo()%></p>
        <p>Usuario <%=usu.getNombreUsuario()%></p>
        <p>Contrasenia <%=usu.getContrasenia()%></p>
        <p>-------------------------------------</p>
        <%cont = cont +1;%>
        <%
            }
        %>
    </body>
</html>
