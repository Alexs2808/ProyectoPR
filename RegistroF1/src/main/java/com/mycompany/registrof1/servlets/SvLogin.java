package com.mycompany.registrof1.servlets;

import conexionTCP.gestionConexiones;
import conexionTCP.servidor;
import conexionUDP.manejadorMensajes;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import logica.Controladora;
import logica.Usuarios;

@WebServlet("/SvLogin")
public class SvLogin extends HttpServlet {

    private final Controladora control = new Controladora();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        HttpSession session = request.getSession(false);
        if (session != null){
            String token = (String)session.getAttribute("tokenTCP");
            if(token!=null){
                servidor.cerrarSesion(token);
            }
            
            manejadorMensajes.limpiar();
            session.invalidate();
        }
        response.sendRedirect("indexx.jsp");
        
    
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String usuario = request.getParameter("NombreUsuario");
        String pass = request.getParameter("Contrasenia");

        if (!control.Comprobacion(usuario, pass)) {
            response.sendRedirect("index.jsp");
            return;
        }

        Usuarios u = control.getUsuario(usuario);
        String equipo = u.getNombreEquipo();

        String tokenTCP = java.util.UUID.randomUUID().toString();

        if (!gestionConexiones.registrarSesionWeb(equipo, tokenTCP)) {
            request.setAttribute("Error",
                    "El equipo " + equipo + " ya tiene 2 dispositivos conectados");
            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }

        HttpSession session = request.getSession(true);
        session.setAttribute("usuario", u);
        session.setAttribute("equipo", equipo);
        session.setAttribute("tokenTCP", tokenTCP);

        response.sendRedirect("principal.jsp");
    }
}
