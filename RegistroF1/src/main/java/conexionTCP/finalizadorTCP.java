package conexionTCP;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class finalizadorTCP implements HttpSessionListener {

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        Object tokenObj = se.getSession().getAttribute("tokenTCP");

        if (tokenObj != null) {
            String token = tokenObj.toString();
            gestionConexiones.liberarPorToken(token);

            System.out.println("Sesion web cerrada, token liberado: " + token);
        }
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
    }
}
