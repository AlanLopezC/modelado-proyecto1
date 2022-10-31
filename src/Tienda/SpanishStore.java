package Tienda;

import Carrito.Carrito;
import Carrito.CarritoBuilder;
import Catalogo.CatalogoProxy;
import Cuenta.CuentaProxy;
import Productos.Producto;
import Remote.Remote;
import TiendaFacade.TiendaFacade;
import Users.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SpanishStore implements Tienda {

    @Override
    public String saludar() {
        return "\n\u250c------------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  ¡Bienvenido a Vuestra Tienda Virtal CheemsMart!. 🏪 \u2502\n" +
                "\u2514------------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String despedirse() {
        return "\n\u250c-------------------------------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  Os agradezco por venid a vuestra Tienda Virtual CheemsMart. Volved Pronto 👋 \u2502\n" +
                "\u2514------------------------------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String mostrarMenu() {
        return "\n\u250c---------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502 1.- Visualizad el Catálogo.     \u2502\n" +
                "\u2502 2.- Haced una compra.           \u2502\n" +
                "\u2502 3.- Cerrad vuestra cuenta.      \u2502\n" +
                "\u2502 4.- Salid del Sistema.          \u2502\n" +
                "\u2514---------------------------------\u2518".replace('-', '\u2500') + "\n"
                + "\nElección: ";
    }

    @Override
    public void realizarCompra(User user){

        // OBJETOS
        CarritoBuilder carritoBuilder = new CarritoBuilder();
        Scanner scannerString = new Scanner(System.in);

        // VARIABLES
        String input;


        while (true) {

            boolean mostroCatalogo = mostrarCatalogo();
            if (!mostroCatalogo){
                break;
            }

            System.out.print("c - Cancelad vuestra compra.\nf - Terminad vuestra compra.\n");
            System.out.print("Poned el barcode del producto que queréis o la letra que queréis: ");
            input = scannerString.nextLine().strip();

            switch (input) {
                case "c": {
                    TiendaFacade.clearConsole();
                    TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                            "\u2502 VUESTRA COMPRA HA SIDO CANCELADA. \u2502\n" +
                            "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                    TiendaFacade.clearConsole();
                    return;
                }
                case "f": compraSegura(carritoBuilder.build(), user); return;
                default:

                    try {

                        Socket socket = new Socket("localhost", 8080);
                        Remote remote = new Remote(socket);
                        CatalogoProxy catalogoProxy = (CatalogoProxy) remote.receive();

                        Producto producto = (Producto) catalogoProxy.getProducto(input);
                        if (producto == null){
                            TiendaFacade.clearConsole();
                            TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                    "\u2502   Vuestro vasto catálogo no contiene ese producto.    \u2502\n" +
                                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                            TiendaFacade.clearConsole();
                            remote.close();
                            continue;
                        }
                        TiendaFacade.clearConsole();
                        TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                                "   Haz agregado a tu furgoneta el producto "   + producto.getNombre() +
                                "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                        TiendaFacade.clearConsole();
                        carritoBuilder.addProduct(producto);


                        remote.close();

                    }catch (IOException e){
                        TiendaFacade.clearConsole();
                        TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                "\u2502      SERVIDOR CAIDO O NO INICIALIZADO.  \u2502\n" +
                                "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
                        TiendaFacade.clearConsole();
                    }

            }
        }

    }

    @Override
    public void compraSegura(Carrito carritoIn, User user){

        Scanner scanner = new Scanner(System.in);

        try {

            Socket socket = new Socket("localhost", 5454);
            Remote remote = new Remote(socket);
            remote.send(user.getUsername());

            CuentaProxy cuentaBancariaProxy = (CuentaProxy) remote.receive();

            TiendaFacade.clearConsole();
            System.out.print("\n\u250c----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502   INICIANDO COMPRA DE VUESTRA CONFIANZA.  \u2502\n" +
                    "\u2514----------------------------------------\u2518".replace('-', '\u2500') + "\n");

            System.out.print("Ingresa vuestra cuenta bancaria: ");
            String cuentaBancaria = scanner.nextLine().strip();

            // NO COINCIDE.
            if (!cuentaBancaria.equals(user.getBankAccount())){
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502     VUESTRO NÚMERO DE CUENTA NO COINCIDE.  \u2502\n" +
                        "\u2514----------------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;
            }

            // RETIRANDO FONDOS.
            Double cargo = cuentaBancariaProxy.retirar(carritoIn.costoTotal());

            if (carritoIn.costoTotal() == 0){

                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                        "         No tened nada agregado a la furgoneta. Volved a intentarlo :( " +
                        "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c-----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502   VUESTRA COMPRA HA SIDO CANCELADA.\u2502\n" +
                        "\u2514-----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;

            }

            if (cargo == 0){

                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                        "         No tened el suficiente dinero :( " +
                        "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502   VUESTRA COMPRA HA SIDO CANCELADA.  \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;
            }

            System.out.print("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502    VUESTRA COMPRA HA SIDO ACEPTADA.   \u2502\n" +
                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
            System.out.print(   "\n----------------------------------------".replace('-', '\u2500') + "\n" +
                    "   VUESTRA FURGONETA: " +
                    "\n----------------------------------------".replace('-', '\u2500') + "\n");
            System.out.print(carritoIn.obtenerCarrito());
            System.out.print(   "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                    "       Vuestra compra llegará el 10/30/2022"  +
                    "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
            TiendaFacade.sleepFor("");
            TiendaFacade.clearConsole();

            remote.send(cuentaBancariaProxy); // ACTUALIZANDO LA CUENTA BANCARIA.
            remote.close();
        }
        catch (IOException e){
            TiendaFacade.clearConsole();
            TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      SERVIDOR CAIDO O NO INICIALIZADO.  \u2502\n" +
                    "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
            TiendaFacade.clearConsole();
        }
    }


    @Override
    public void salirCerrarSesion(User user) {
        TiendaFacade.clearConsole();
        TiendaFacade.sleepFor("\n\u250c-----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                    "\u2502     CERRANDO VUESTRA SESIÓN       \u2502\n" +
                                    "\u2514-----------------------------------\u2518".replace('-', '\u2500') + "\n");
        System.out.print("\nCerrando Sesión.\n");
        TiendaFacade.clearConsole();
        user = null;

    }

    @Override
    public void mostrarOferta(){

    }

}
