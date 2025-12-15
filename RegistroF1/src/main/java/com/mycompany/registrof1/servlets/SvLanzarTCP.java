package com.mycompany.registrof1.servlets;

import conexionUDP.clienteUDP;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/SvLanzarTCP")
public class SvLanzarTCP extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String token = req.getParameter("tokenTCP");
        String comando = "java -jar ClienteTCP.jar " + token;
        Runtime.getRuntime().exec(comando);

    
        clienteUDP.enviarMensaje("Bienvenido a la carrera de tu vida");
        resp.sendRedirect("principal.jsp");
    }
}
