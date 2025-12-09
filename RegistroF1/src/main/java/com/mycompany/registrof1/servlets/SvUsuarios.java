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
import logica.Usuarios;


@WebServlet(name = "SvUsuarios", urlPatterns = {"/SvUsuarios"})
public class SvUsuarios extends HttpServlet {

    Controladora control = new Controladora();
    //esta instancia permite conectar con los metodos de la clase para que los 
    //pueda usar post y guet

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //simulacion de base de datos para prueba
        List<Usuarios>ListaUsuarios=new ArrayList<>();
        ListaUsuarios = control.traerUsuarios();
        /*ListaUsuarios.add(new Usuarios("RedBull","RBLL","123"));
        ListaUsuarios.add(new Usuarios("Ferrari","FRR","153"));*/
        
        HttpSession misesion = request.getSession();//De la request traeme la sesion
        misesion.setAttribute("listaUsuarios", ListaUsuarios);
        //trae la sesion/identificacion del usuario y setear la lista de usuarios
        
        response.sendRedirect("MostrarUsuarios.jsp");
        //reenvia 
        
        
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
            //primero recibimos los valores del formulario
            String NombreEquipo = request.getParameter("NombreEquipo"); //getParameter --> Traeme el parametro
            String NombreUsuario = request.getParameter("NombreUsuario");
            String Contrasenia = request.getParameter("Contrasenia");
            /*System.out.println(NombreEquipo);
            System.out.println(NombreUsuario);
            System.out.println(Contrasenia);*/
            
            control.crearUsuario(NombreEquipo, NombreUsuario, Contrasenia);
            
            response.sendRedirect("indexx.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
