package conexionUDP;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;


public class manejadorMensajes {

    private static final Queue<String> mensajes = new ConcurrentLinkedQueue<>();

    public static void agregar(String mensaje) {
        mensajes.add(mensaje);
    }

    public static String obtener() {
        return mensajes.poll(); // saca y elimina
    }

    public static boolean hayMensajes() {
        return !mensajes.isEmpty();
    }

    public static void limpiar(){
        mensajes.clear();
    }
    
}
