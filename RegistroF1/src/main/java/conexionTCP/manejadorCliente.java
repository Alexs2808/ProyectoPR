package conexionTCP;

import java.io.*;
import java.net.Socket;
import java.util.List;
import logica.Controladora;
import logica.Pilotos;

public class manejadorCliente extends Thread{
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private Controladora control;
    private String idEquipo;
    
    public manejadorCliente(Socket soc, Controladora ctrl){
        this.socket = soc;
        this.control=ctrl;
    }
    
    @Override
    public void run(){
        try{
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
        
        
            String equipo = in.readLine();
            
            if(!gestionConexiones.registroDispositivo(equipo)){
                out.println("Ya hay dos dispositivos para este equipo");
                socket.close();
                return;
            }
            this.idEquipo = equipo; //guarda el id del equipo(no dispositivo)
            out.println("Dispositivo registrado");
            
            //entrada de comandos por el usuario
            String comando;
            while((comando = in.readLine())!=null){
                if(comando.equals("GET_PILOTOS")){
                    List<Pilotos>listaPilotos = control.traerPilotos();
                    out.println(listaPilotos.toString());
                }
            }
            
            if(comando.startsWith("REG_TIEMPO: ")){
                
            }
        
        }catch(IOException ioe){
             // Imprime el error solo si el dispositivo se había registrado.
             if(this.idEquipo != null) {
                 System.out.println("Cliente (" + this.idEquipo + ") desconectado inesperadamente.");
             } else {
                 ioe.printStackTrace();
             }
        } finally {
            // 3. Liberación de la conexión con el ID correcto
            if (this.idEquipo != null) {
                gestionConexiones.liberacionDispositivos(this.idEquipo);
            }
            // Asegurar que el socket se cierra
            try {
                if (socket != null && !socket.isClosed()) {
                    socket.close();
                }
            } catch (IOException e) {
                 // Ignorar errores al cerrar el socket
            }
        }
    }
}
    