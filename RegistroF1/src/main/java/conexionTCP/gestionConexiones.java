package conexionTCP;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class gestionConexiones {

    private static final Map<String, Integer> dispositivosPorEquipo =
            new ConcurrentHashMap<>();

    private static final Map<String, String> tokenEquipo =
            new ConcurrentHashMap<>();


    public static synchronized boolean registrarSesionWeb(
            String equipo, String token) {

        int activos = dispositivosPorEquipo.getOrDefault(equipo, 0);

        if (activos >= 2) {
            return false;
        }

        dispositivosPorEquipo.put(equipo, activos + 1);
        tokenEquipo.put(token, equipo);

        System.out.println("Sesion registrada: " + token + " -> " + equipo);
        return true;
    }


    public static synchronized String validarToken(String token) {
        return tokenEquipo.get(token);
    }

    public static synchronized void liberarPorToken(String token) {
        String equipo = tokenEquipo.remove(token);
        if (equipo != null) {
            int activos = dispositivosPorEquipo.getOrDefault(equipo, 1) - 1;
            if (activos <= 0) {
                dispositivosPorEquipo.remove(equipo);
            } else {
                dispositivosPorEquipo.put(equipo, activos);
            }
        }
    }

    public static synchronized void limpiarServidor() {
        dispositivosPorEquipo.clear();
        tokenEquipo.clear();
    }
}
