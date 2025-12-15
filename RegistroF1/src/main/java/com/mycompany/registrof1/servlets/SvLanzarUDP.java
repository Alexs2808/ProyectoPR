package com.mycompany.registrof1.servlets;

import conexionUDP.clienteUDP;
import conexionUDP.manejadorMensajes;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "SvLanzarUDP", urlPatterns = {"/SvLanzarUDP"})
public class SvLanzarUDP extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            
        response.setContentType("text/plain");

        if (manejadorMensajes.hayMensajes()) {
            response.getWriter().write(manejadorMensajes.obtener());
        } else {
            response.getWriter().write("");
        }
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String token = request.getParameter("tokenTCP");
        clienteUDP.enviarMensaje(token);
        response.sendRedirect("principal.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
