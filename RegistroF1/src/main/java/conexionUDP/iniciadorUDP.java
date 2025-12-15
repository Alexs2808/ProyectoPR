package conexionUDP;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class iniciadorUDP implements ServletContextListener {

    private Thread hiloUDP;
    private servidorUDP servidor;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servidor = new servidorUDP();

        hiloUDP = new Thread(new Runnable() {
            @Override
            public void run() {
                servidor.iniciar();
            }
        });

        hiloUDP.start();
        System.out.println("Servidor UDP iniciado");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("Apagando servidor UDP...");
        servidor.detener();
    }
}
