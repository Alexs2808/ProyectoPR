package conexionTCP;

import java.net.*;
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import logica.Controladora;

public class servidor {
    
    private Controladora ctrl = new Controladora();
    
    public static void main(String[] args){
        servidor serv = new servidor();
        serv.iniciar();
    }

    public void iniciar(){
        int puerto = 12245;
        try(ServerSocket server = new ServerSocket(puerto)){
            System.out.println("Servidor corriendo, esuchando en el puerto: " + puerto);
            
            while(true){
                Socket soc = server.accept();
                
                new Thread(new manejadorCliente(soc, ctrl)).start();
            }
        }catch(IOException ioe){
            ioe.printStackTrace();
        }
    }

    
    
    
}
