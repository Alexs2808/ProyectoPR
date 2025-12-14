package conexionTCP;

import javax.servlet.*;
import javax.servlet.annotation.WebListener;

@WebListener
public class iniciadorTCP implements ServletContextListener {

    private Thread tcpThread;
    private servidor servidorTCP;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        servidorTCP = new servidor();

        tcpThread = new Thread(new Runnable() {
            @Override
            public void run() {
                servidorTCP.iniciar();
            }
        });

        tcpThread.setDaemon(true);
        tcpThread.start();

        System.out.println("Servidor TCP iniciado desde Tomcat");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        servidorTCP.detener();
        gestionConexiones.limpiarServidor();

        try {
            tcpThread.join(3000);
        } catch (InterruptedException ignored) {}

        System.out.println("Servidor TCP detenido por Tomcat");
    }
}
