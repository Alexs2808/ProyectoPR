package com.mycompany.registrof1.servlets;

import conexionTCP.gestionConexiones;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Usuarios;


@WebServlet(name = "SvLogin", urlPatterns = {"/SvLogin"})
public class SvLogin extends HttpServlet {

    Controladora control = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
           
            String NombreUsuario = request.getParameter("NombreUsuario");
            String Contrasenia = request.getParameter("Contrasenia");
            
            
            boolean validacion = control.Comprobacion(NombreUsuario, Contrasenia);
            
            if(validacion){
                
                Usuarios Equipo = control.getUsuario(NombreUsuario);
                String idEquipo = Equipo.getNombreEquipo();

                if(gestionConexiones.registroDispositivo(idEquipo)){
                    HttpSession misesion = request.getSession();
                    misesion.setAttribute("equipoLogeado", Equipo);
                    misesion.setAttribute("idEquipo", idEquipo);
                    response.sendRedirect("principal.jsp");
                }else{
                    request.setAttribute("Error", "El equipo: " + idEquipo + "tiene dos dispositivos conectados");
                }
            }else{
                response.sendRedirect("error.jsp");//aca deberia mandar a una pagina que salte un error
            } 

        
    }

    
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
