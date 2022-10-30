package Tienda;

import Carrito.Carrito;
import Catalogo.CatalogoProxy;
import Cuenta.CuentaProxy;
import Remote.Remote;
import Users.User;

import java.io.IOException;
import java.net.Socket;

public interface Tienda {

    String saludar();

    String despedirse();

    String mostrarMenu();

    default void mostrarCatalogo(){

        try {

            Socket socket = new Socket("localhost", 8080);
            Remote remote = new Remote(socket);
            CatalogoProxy catalogoProxy = (CatalogoProxy) remote.receive();
            // FALTA PANTALLA DE CARGA.
            catalogoProxy.mostrarCatalogo();
            remote.close();

        }catch (IOException e){
            System.out.print("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      SERVIDOR CAIDO O NO INICIALIZADO.  \u2502\n" +
                    "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
        }
    }

    void realizarCompra(CatalogoProxy catalogoProxyIn, User user);

    void compraSegura(Carrito carritoIn, User user);

    void salirCerrarSesion(User user);

    String salirSistema();

    void mostrarOferta(CatalogoProxy catalogoProxy);
}
