package com.mycompany.registrof1.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.Controladora;
import logica.Pilotos;
import logica.Pistas;
import logica.Practicas;
import logica.TipoTester;


@WebServlet(name = "SvPracticas", urlPatterns = {"/SvPracticas"})
public class SvPracticas extends HttpServlet {
    
    Controladora control = new Controladora();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        List<Pistas>listaPistas = control.traerPistas();
        List<Pilotos>listaPilotos = control.traerPilotos();
        List<TipoTester>listaTesters = control.traerTester();

        request.setAttribute("listaPistas", listaPistas);
        request.setAttribute("listaPilotos", listaPilotos);
        request.setAttribute("listaTesters", listaTesters);
        
        request.getRequestDispatcher("registroDatos.jsp?ok=true").forward(request, response);

    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            try{
                
                int Pista = Integer.parseInt(request.getParameter("Pista"));
                int Piloto = Integer.parseInt(request.getParameter("Piloto"));
                int Tester = Integer.parseInt(request.getParameter("Tester"));
                //String Fecha = request.getParameter("Fecha");

                Practicas pr =control.crearPractica(Pista, Piloto, Tester);
                HttpSession misesion = request.getSession();
                misesion.setAttribute("Practic", pr.getIdPractica());

                response.sendRedirect("SvPracticas?ok=true");
                
            }catch(Exception e){
                e.printStackTrace();
                response.sendRedirect("SvPracticas?error=ErrorGuardado");

            }
        

    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
