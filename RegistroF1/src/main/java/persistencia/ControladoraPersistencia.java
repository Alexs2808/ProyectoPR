package persistencia;

import java.util.List;
import javax.persistence.TypedQuery;
import logica.Pilotos;
import logica.Usuarios;

public class ControladoraPersistencia {
    
    UsuariosJpaController usuJPA = new UsuariosJpaController();
    TipoTesterJpaController tipoTesJPA = new TipoTesterJpaController();
    TiemposSectorJpaController tieSecJPA = new TiemposSectorJpaController();
    PracticasJpaController pracJPA = new PracticasJpaController();
    PistasJpaController pistaJPA = new PistasJpaController();
    PilotosJpaController pilJPA = new PilotosJpaController();
    //JSP --> SERVLET --> CONTROLADORA_LOGICA --> CONTROLADORA_PERSISTENCIA --> JPA --> REALIZA TODAS LAS ACCIONES EN LA BD

    
    public ControladoraPersistencia(){
    
    }
    
    /*-----------------METODOS EQUIPOS----------------*/
    
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
    
    public Usuarios getUsuarios(String nombreUsuario){
        try{
            TypedQuery<Usuarios> query = usuJPA.getEntityManager().createQuery(
                "SELECT u FROM Usuarios u WHERE u.nombreUsuario = :nombre",
                Usuarios.class
            );
        query.setParameter("nombre", nombreUsuario);
        return query.getSingleResult();
        }catch(Exception e){
            return null;
        }
    }
    
    /*-------METODOS PILOTOS--------------*/
    
    public void crearPiloto(Pilotos pil){
        pilJPA.create(pil);
    }
    
}
