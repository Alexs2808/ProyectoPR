package logica;

import java.util.List;
import persistencia.ControladoraPersistencia;
import logica.Usuarios;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    //JSP --> SERVLET --> CONTROLADORA_LOGICA --> CONTROLADORA_PERSISTENCIA --> JPA --> REALIZA TODAS LAS ACCIONES EN LA BD
    public void crearUsuario(Usuarios usu){
        controlPersis.crearUsuario(usu);
    }
    
    public List<Usuarios>traerUsuarios(){
        return controlPersis.traerUsuarios();
    }
    
}
