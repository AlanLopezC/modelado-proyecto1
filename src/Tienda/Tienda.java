package Tienda;

import Carrito.Carrito;
import Catalogo.CatalogoProxy;
import Productos.Producto;
import Remote.Remote;
import TiendaFacade.TiendaFacade;
import Users.User;

import java.io.IOException;
import java.net.Socket;

public interface Tienda {

    /**
     * La tienda saluda al usuario en su respectivo Idioma.
     * @return String - Saludo.
     */
    String saludar();

    /**
     * La tienda se despide del usuario en su respectivo Idioma.
     * @return String - Despedida.
     */
    String despedirse();

    /**
     * Método que te muestra el menú en su respectivo Idioma.
     * @return String - Menú.
     */
    String mostrarMenu();

    /**
     * Método que muestra el catálogo.
     * @return Boolean - True si mostro el catálogo.
     */
    default boolean mostrarCatalogo(){

        boolean mostroCatalogo = false;

        try {

            Socket socket = new Socket("localhost", 8080);
            Remote remote = new Remote(socket);
            CatalogoProxy catalogoProxy = (CatalogoProxy) remote.receive();
            // FALTA PANTALLA DE CARGA.
            catalogoProxy.mostrarCatalogo();
            remote.close();
            mostroCatalogo = true;

        }catch (IOException e){
            TiendaFacade.clearConsole();
            TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      SERVIDOR CAIDO O NO INICIALIZADO.  \u2502\n" +
                    "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
            TiendaFacade.clearConsole();
        }
        return mostroCatalogo;
    }

    /**
     * Método para realizar una compra en la Tienda con el respectivo Idioma del Usuario.
     * @param user - User que hará la compra.
     */
    void realizarCompra(User user);

    /**
     * Método para realizar una compra segura en la Tienda.
     * @param carritoIn - Carrito que contiene los Productos Elegidos por el usuario.
     * @param user - Usuario que va a realizar la compra Segura.
     */
    void compraSegura(Carrito carritoIn, User user);

    /**
     * Método para cerrar Sesión del Usuario.
     * @param user - Usuario que va a cerrar sesión.
     */
    void salirCerrarSesion(User user);

    /**
     * Método para mostrar una oferta disponible respectivo al país del Usuario.
     */
    void mostrarOferta();

}
