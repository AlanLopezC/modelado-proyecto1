package Cuenta;
import java.net.*;
import java.io.*;
import java.util.*;
import Remote.Remote;


public class SimulacionC{
	
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

    public static void startServer() {
        System.out.println("***Servidor del VVBA***");
        try{
            ServerSocket server = new ServerSocket(5454);
            Cuenta cuenta1 = new Cuenta(211, "jorge", 2000);
            Cuenta cuenta2 = new Cuenta(111, "SaJo", 20000);

            ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
            cuentas.add(cuenta1);
            cuentas.add(cuenta2);

            while(true){
                Socket s = server.accept();
                Remote rc = new Remote(s);
                String usuario = (String)rc.receive();
                CuentaProxy proxy = null;
                for(int i = 0; i < cuentas.size();i++){
                    if(cuentas.get(i).getusuario().equals(usuario)){
                        Cuenta cuentaEnviar = cuentas.get(i);
                        proxy = new CuentaProxy(cuentaEnviar); 
                         break;
                    }else{
                        proxy = null;
                    }

                }                
                rc.send(proxy);
                System.out.println("***ENVIO EXITOSO***");
                proxy = (CuentaProxy) rc.receive();
                proxy.actualizarRetiro();
                
                

                rc.close();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void startClient() {
        System.out.println("***Tienda virtual***");
        try{
            Socket s = new Socket("localhost", 5454);
            Remote rc = new Remote(s);
            String usuario = "SaJo";
            rc.send(usuario);
            System.out.println("***ENVIO  DE USUARIO EXITOSO***");
            System.out.println("***OBTENIENDO CUENTA...***");
            CuentaProxy cuentaProxy = (CuentaProxy)rc.receive();
            cuentaProxy.retirar(100);
            rc.send(cuentaProxy);
            rc.close();
        }catch (UnknownHostException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}