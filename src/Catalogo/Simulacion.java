package Catalogo;
import java.net.*;
import java.io.*;

import Remote.Remote;
public class Simulacion{
	
	public static void main(String[] args) {
        /*Al correr el programa escribe "server" o "client".
          java SimulacionMomazon server en una terminal
          java SimulacionMomazon client en otra,*/
        
        if ("server".equals(args[0])) {
            startServer();
        } else if("client".equals(args[0])){
            startClient();
        }

    }

    public static void startClient() {
        System.out.println("***cliente***");
        try{
            Socket s = new Socket("localhost", 8080);
            Remote r = new Remote(s);
            System.out.println("CARGANDO CATÁLOGO...");
            CatalogoProxy proxy = (CatalogoProxy)r.receive();
            System.out.println("CARGANDO CATÁLOGO...");
            proxy.mostrarCatalogo();
            r.close();  
            
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void startServer() {
        System.out.println("***SERVIDOR DEL CATALOGO***");
        try{
            ServerSocket server = new ServerSocket(8080);
            while(true){
            Socket s = server.accept();
            Remote r = new Remote(s);
            Catalogo catalogoEnviar = new Catalogo();
        
            CatalogoProxy proxy = new CatalogoProxy(catalogoEnviar);
            System.out.println("*** ENVIANDO CATALOGO...***");
            r.send(proxy);
            System.out.println("***ENVIO EXITOSO***");
            System.out.println("");
            r.close();
            }
        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}