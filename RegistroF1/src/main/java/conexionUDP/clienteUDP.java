package conexionUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class clienteUDP {
     public static void enviarMensaje(String mensaje) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serverAddress = InetAddress.getByName("localhost");

            byte[] datos = mensaje.getBytes();

            DatagramPacket datagrama =
                    new DatagramPacket(datos, datos.length, serverAddress, 6000);

            socket.send(datagrama);
            socket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
