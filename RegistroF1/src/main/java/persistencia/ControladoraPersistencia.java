package persistencia;

import java.util.List;
import logica.Usuarios;

public class ControladoraPersistencia {
    
    UsuariosJpaController usuJPA = new UsuariosJpaController();
    TipoTesterJpaController tipoTesJPA = new TipoTesterJpaController();
    TiemposSectorJpaController tieSecJPA = new TiemposSectorJpaController();
    PracticasJpaController pracJPA = new PracticasJpaController();
    PistasJpaController pistaJPA = new PistasJpaController();
    PilotosJpaController pilotos = new PilotosJpaController();
    //JSP --> SERVLET --> CONTROLADORA_LOGICA --> CONTROLADORA_PERSISTENCIA --> JPA --> REALIZA TODAS LAS ACCIONES EN LA BD

    
    public ControladoraPersistencia(){
    
    }
    
    
    //create
    public void crearUsuario(Usuarios usu){
        usuJPA.create(usu);
    }
    
    //read
    public List<Usuarios>traerUsuarios(){
        //Trae todos los usuarios cargados en la bd
        return usuJPA.findUsuariosEntities();
    }
    
    //update
    
    
    //delete

    
}
