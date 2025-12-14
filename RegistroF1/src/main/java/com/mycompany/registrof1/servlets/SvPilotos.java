package com.mycompany.registrof1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Pilotos;
import logica.Usuarios;



@WebServlet(name = "SvPilotos", urlPatterns = {"/SvPilotos"})
public class SvPilotos extends HttpServlet {

    Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        /*------METODO PARA MOSTRAR LOS DATOS EN mostrarDatos.jsp -------*/
        List<Pilotos>listaPilotos = control.traerPilotos();
        
        request.setAttribute("listaPilotos", listaPilotos);
        
        request.getRequestDispatcher("gestionPilotos.jsp").forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

                String NombrePiloto = request.getParameter("NombrePiloto");
                String ApellidoPaterno = request.getParameter("ApellidoPaterno");
                String ApellidoMaterno = request.getParameter("ApellidoMaterno");
                String Nacionalidad = request.getParameter("Nacionalidad");
                String Edadstr = request.getParameter("Edad");
                int Edad = 0;
                
                if(Edadstr == null || Edadstr.trim().isEmpty()){
                    response.sendRedirect("registroPilotos.jsp?error=edadFaltante");
                    return;
                }
                try{
                Edad = Integer.parseInt(request.getParameter("Edad"));
                //para un int debe verse asi                
                }catch(NumberFormatException e){
                    response.sendRedirect("registroPilotos.jsp?error=edadInvalida");
                    return;                
                }
                

                
                HttpSession misesion = request.getSession();//De la request traeme la sesion
                Usuarios EquipoLogeado = (Usuarios) misesion.getAttribute("equipoLogeado");

                control.crearPiloto(NombrePiloto, ApellidoPaterno, ApellidoMaterno, 
                        Nacionalidad, Edad, EquipoLogeado);

                response.sendRedirect("principal.jsp");


    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
