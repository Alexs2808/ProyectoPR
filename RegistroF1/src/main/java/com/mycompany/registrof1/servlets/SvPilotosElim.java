package com.mycompany.registrof1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import logica.Controladora;


@WebServlet(name = "SvPilotosElim", urlPatterns = {"/SvPilotosElim"})
public class SvPilotosElim extends HttpServlet {
        
    Controladora control = new Controladora();


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
                
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            int id = Integer.parseInt(request.getParameter("id"));

            control.desactivarPiloto(id);
            response.sendRedirect("SvPilotos?desativados=ok"); 
        } catch (NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("SvPilotos?error=idInvalido");
        }catch(Exception e){
             e.printStackTrace();
            response.sendRedirect("SvPilotos?error=idInvalido");           
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
