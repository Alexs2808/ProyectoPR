package conexionTCP;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;



@WebListener
public class iniciadorTCP implements ServletContextListener {
    
    private Thread tcp;
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
       tcp = new Thread(new Runnable(){
         public void run(){
             new servidor().iniciar();
         }   
        });
        tcp.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        if(tcp !=null && tcp.isAlive()){
            tcp.interrupt();
            }
    }
}
