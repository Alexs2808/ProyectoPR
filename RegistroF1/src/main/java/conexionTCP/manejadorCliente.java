package conexionTCP;

import java.io.*;
import java.net.Socket;
import logica.Controladora;

public class manejadorCliente extends Thread {

    private final Socket socket;
    private final Controladora control;
    private String token;

    public manejadorCliente(Socket socket, Controladora control) {
        this.socket = socket;
        this.control = control;
        setDaemon(true);
    }

    @Override
    public void run() {

        try (BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                 socket.getOutputStream(), true)) {

            String linea = in.readLine();

            if (!linea.startsWith("TOKEN:")) {
                out.println("TOKEN NO ENVIADO");
                return;
            }

            token = linea.substring(6);
            String equipo = gestionConexiones.validarToken(token);

            if (equipo == null) {
                out.println("TOKEN INVALIDO");
                return;
            }

            out.println("CONECTADO AL EQUIPO: " + equipo);

            while ((linea = in.readLine()) != null) {
                if (linea.equalsIgnoreCase("SALIR")) break;
                out.println("Comando no reconocido");
            }

        } catch (IOException ignored) {
        } finally {
            if (token != null) {
                gestionConexiones.liberarPorToken(token);
            }
        }
    }
}
