import Catalogo.Catalogo;
import Catalogo.CatalogoProxy;
import Cuenta.Cuenta;
import Cuenta.CuentaProxy;
import Remote.Remote;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class BaseDeDatos {
    
    public static void main(String[] args) {

        if ("catalogo".equals(args[0])) {
            startServerCatalogo();
        }
        else if ("cuenta".equals(args[0])){
            startServerCuentas();
        }
    }

    public static void startServerCatalogo(){

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

    public static void startServerCuentas(){
        System.out.println("***Servidor del VVBA***");
        try{
            ServerSocket server = new ServerSocket(5454);

            Cuenta cuenta1 = new Cuenta(211, "alanlopez", 2000);
            Cuenta cuenta2 = new Cuenta(111, "jonathanm", 20000);
            Cuenta cuenta3 = new Cuenta(354, "marmar", 2000);

            ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
            cuentas.add(cuenta1);
            cuentas.add(cuenta2);
            cuentas.add(cuenta3);

            while(true){

                Socket s = server.accept();
                Remote rc = new Remote(s);
                String usuario = (String)rc.receive();
                CuentaProxy proxy = null;
                for (Cuenta cuenta : cuentas) {
                    if (cuenta.getusuario().equals(usuario)) {
                        Cuenta cuentaEnviar = cuenta;
                        proxy = new CuentaProxy(cuentaEnviar);
                        break;
                    } else {
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
}
