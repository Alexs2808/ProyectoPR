package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.TypedQuery;
import logica.Pilotos;
import logica.Pistas;
import logica.Practicas;
import logica.TiemposSector;
import logica.TipoTester;
import logica.Usuarios;
import persistencia.exceptions.NonexistentEntityException;

public class ControladoraPersistencia {
    
    UsuariosJpaController usuJPA = new UsuariosJpaController();
    TipoTesterJpaController tipoTestJPA = new TipoTesterJpaController();
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
        //metodo para crear a los pilotos
    }
    
    public List<Pilotos>traerPilotos(){
        //Trae todos los usuarios cargados en la bd
        return pilJPA.findPilotosEntities();
    }
    
    public Pilotos buscarPilotos(int Piloto){
        return pilJPA.findPilotos(Piloto);
    }
    
    
    public List<Pilotos>getPilotos(){
        return pilJPA.findPilotosEntities();
    }
    
    public void borrarPiloto(int id){
        try {
            pilJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /*--------METODOS PISTAS----------*/
    
    public List<Pistas>traerPistas(){
        return pistaJPA.findPistasEntities();
    }
    
    public Pistas buscarPista(int Pista){
        return pistaJPA.findPistas(Pista);
    }
   
    
    /*---------METODOS TIPOS DE TEST---------*/
    
    public List<TipoTester>traerTesters(){
        return tipoTestJPA.findTipoTesterEntities();
    }
    
    public TipoTester buscarTest(int Tester){
        return tipoTestJPA.findTipoTester(Tester);
    }
    
    /*--------------METODOS PRACTICAS-------------*/    
    
    public void crearPractica(Practicas prac){
        pracJPA.create(prac);
    }
    
    public Practicas buscarPracticas(int Practica){
        return pracJPA.findPracticas(Practica);
    }
    
    public void crearTest(TiemposSector ts){
    //me equivoque de nombre, era crear tiempo o sector    
        tieSecJPA.create(ts);
    }
    
    public void borrarPracticasConPilotos(int id){
        try {
            pracJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
