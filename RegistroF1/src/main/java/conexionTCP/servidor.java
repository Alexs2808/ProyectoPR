package conexionTCP;

import java.net.*;
import java.io.*;
import logica.Controladora;

public class servidor {

    private final Controladora ctrl = new Controladora();
    private ServerSocket serverSocket;
    private volatile boolean ejecutando = false;
    private final int puerto = 12245;

    public void iniciar() {
        ejecutando = true;

        try {
            serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor TCP en puerto " + puerto);

            while (ejecutando) {
                try {
                    Socket socket = serverSocket.accept();
                    new manejadorCliente(socket, ctrl).start();
                } catch (SocketException se) {
                    if (ejecutando) se.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void detener() {
        ejecutando = false;
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException ignored) {}
    }
}
    