package com.mycompany.registrof1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Pilotos;


@WebServlet(name = "SvPilotosEdit", urlPatterns = {"/SvPilotosEdit"})
public class SvPilotosEdit extends HttpServlet {

    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int id = Integer.parseInt(request.getParameter("id"));
        Pilotos piloto = control.buscarPilotos(id);
        
        HttpSession misesion = request.getSession();
        misesion.setAttribute("Editar", piloto);
        
        response.sendRedirect("editarPilotos.jsp");
        
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
                Pilotos pil = (Pilotos)request.getSession().getAttribute("Editar");
                
                pil.setNombrePiloto(NombrePiloto);
                pil.setApellidoPaterno(ApellidoPaterno);
                pil.setApellidoMaterno(ApellidoMaterno);
                pil.setNacionalidad(Nacionalidad);
                pil.setEdad(Edad);
                
                control.editarPiloto(pil);
                
                response.sendRedirect("SvPilotos");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
