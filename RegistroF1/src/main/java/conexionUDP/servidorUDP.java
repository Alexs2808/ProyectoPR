package conexionUDP;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class servidorUDP {

    private static final int PUERTO = 6000;
    private DatagramSocket socket;
    private volatile boolean activo = true;

    public void iniciar() {
        try {
            socket = new DatagramSocket(PUERTO);
            System.out.println("Servidor UDP escuchando en puerto " + PUERTO);

            while (activo) {
                byte[] datos = new byte[1024];
                DatagramPacket packet = new DatagramPacket(datos, datos.length);

                socket.receive(packet);

                String mensaje = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Mensaje recibido: " + mensaje);

                String respuesta = "Bienvenido a la carrera de tu vida";
                manejadorMensajes.agregar(respuesta);

                byte[] resp = respuesta.getBytes();
                DatagramPacket response = new DatagramPacket(
                        resp, resp.length,
                        packet.getAddress(),
                        packet.getPort()
                );

                socket.send(response);
            }

        } catch (Exception e) {
            if (activo) {
                e.printStackTrace();
            }
        } finally {
            if (socket != null && !socket.isClosed()) {
                socket.close();
            }
        }
    }

    public void detener() {
        activo = false;
        if (socket != null) {
            socket.close();
        }
        System.out.println("Servidor UDP detenido correctamente");
    }
}
