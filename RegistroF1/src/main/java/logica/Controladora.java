package logica;

import java.util.ArrayList;
import java.util.List;
import persistencia.ControladoraPersistencia;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


public class Controladora {
    
    ControladoraPersistencia controlPersis = new ControladoraPersistencia();
    //JSP --> SERVLET --> CONTROLADORA_LOGICA --> CONTROLADORA_PERSISTENCIA --> JPA --> REALIZA TODAS LAS ACCIONES EN LA BD
    
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); 
    
    public void crearUsuario(String nombreEquipo, String nombreUsuario, String Contrasenia){
        
        String ContraseniaSegura = passwordEncoder.encode(Contrasenia);
        
        Usuarios usu = new Usuarios();
        usu.setNombreEquipo(nombreEquipo);
        usu.setNombreUsuario(nombreUsuario);
        usu.setContrasenia(ContraseniaSegura);
        controlPersis.crearUsuario(usu);
    }
    
    public List<Usuarios>traerUsuarios(){
        return controlPersis.traerUsuarios();
    }
    
    public boolean Comprobacion(String NombreUsuario, String Contrasenia) {
        
        boolean ingreso = false;
        
        List<Usuarios>traerEquipos = new ArrayList<Usuarios>();
        traerEquipos = controlPersis.traerUsuarios();
        
        for(Usuarios usu:traerEquipos){
            if(usu.getNombreUsuario().equals(NombreUsuario)){
                if(usu.getContrasenia()!=null && passwordEncoder.matches(Contrasenia, usu.getContrasenia())){
                    ingreso = true;
                }else{
                    ingreso=false;
                }
                
            }
        }
        
        return ingreso;
    }
    
}
