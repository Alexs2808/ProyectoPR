package conexionTCP;

import java.io.*;
import java.net.Socket;

public class clienteTCP {

    public static void main(String[] args) {

        if (args.length == 0) {
            System.out.println("Token no recibido");
            return;
        }

        String token = args[0];

        try (Socket socket = new Socket("localhost", 12245);
             BufferedReader in = new BufferedReader(
                 new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(
                 socket.getOutputStream(), true)) {

            out.println("TOKEN:" + token);

            String msg;
            while ((msg = in.readLine()) != null) {
                System.out.println("Servidor: " + msg);
            }

        } catch (IOException e) {
            System.out.println("TCP cerrado");
        }
    }
}
