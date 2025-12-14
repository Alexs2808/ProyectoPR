package com.mycompany.registrof1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvTester", urlPatterns = {"/SvTester"})
public class SvTester extends HttpServlet {
    
    Controladora control = new Controladora();

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try{
            int Practic = Integer.parseInt(request.getParameter("Practic"));
            int Vuelta = Integer.parseInt(request.getParameter("Vuelta"));
            int Sector = Integer.parseInt(request.getParameter("Sector"));
            float TiempoSector = Float.parseFloat(request.getParameter("TiempoSector"));
            float Distancia = Float.parseFloat(request.getParameter("Distancia"));

            control.crearTest(Practic, Vuelta, Sector, TiempoSector, Distancia);
            //me equivoque de nombre, era crear tiempo o sector    
            response.sendRedirect("SvPracticas?ok=true");            
            }catch (NumberFormatException e){
                response.sendRedirect("SvTester?error=datosInvalidos");                
            
            }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("SvTester?error=ErrorGuardado");

            }
        

        
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
