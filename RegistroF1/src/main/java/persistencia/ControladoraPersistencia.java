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
    try {
            TypedQuery<Pilotos> query = pilJPA.getEntityManager().createQuery(
                "SELECT p FROM Pilotos p WHERE p.activo = true ORDER BY p.nombrePiloto",
                Pilotos.class
            );
            return query.getResultList();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
    
    public Pilotos buscarPilotos(int Piloto){
        return pilJPA.findPilotos(Piloto);
    }
    
    
    public List<Pilotos>getPilotos(){
        return pilJPA.findPilotosEntities();
    }
    
    public void desactivarPiloto(int id) throws Exception{
        try {
            Pilotos piloto = pilJPA.findPilotos(id);
            if(piloto!=null){
                piloto.setActivo(false);
                pilJPA.edit(piloto);
            }
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    
    public void editarPiloto(Pilotos pil) {
        try {
            pilJPA.edit(pil);
        } catch (Exception ex) {
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
    
    /*public void borrarPracticasConPilotos(int id){
        try {
            pracJPA.destroy(id);
        } catch (NonexistentEntityException ex) {
            Logger.getLogger(ControladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
    public List<Practicas> traerPracticas(){
        try {
            TypedQuery<Practicas> query = pracJPA.getEntityManager().createQuery(
                    "SELECT p FROM Practicas p " +
                    "JOIN FETCH p.piloto pil " +
                    "JOIN FETCH p.pista pista " +
                    "JOIN FETCH p.tipoTester tt " +
                    "LEFT JOIN FETCH p.ListaTiemposSector ts " + 
                    "ORDER BY p.idPractica, ts.numVuelta",
                    Practicas.class 
                    );
                return query.getResultList();
            } catch (Exception e) {
                return new ArrayList<>();
            }
    }
}
