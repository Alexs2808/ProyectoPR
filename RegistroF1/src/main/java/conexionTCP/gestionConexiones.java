package conexionTCP;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


public class gestionConexiones {
    private static  Map<String, Integer>dispositivosActivos = new ConcurrentHashMap<>();
    
    public static synchronized boolean registroDispositivo(String equipo) {
        int contador = dispositivosActivos.getOrDefault(equipo, 0);
        
        if(contador >= 2)
            return false;
        
        dispositivosActivos.put(equipo, contador+1);
        return true;
    }

    public static synchronized void liberacionDispositivos(String equipo) {
        int contador = dispositivosActivos.getOrDefault(equipo, 0);
        
        if(contador > 0){
        dispositivosActivos.put(equipo, contador-1);
        }
    }

}
