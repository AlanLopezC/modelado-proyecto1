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
import java.util.Random;
import java.util.Scanner;

public class LatinSpanishStore implements Tienda {

    private String barCodeFinal; // BARCODE DEL PRODUCTO CON DESCUENTO.

    @Override
    public String saludar() {
        return "\n\u250c--------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  ¡Bienvenido a la Tienda Virtual CheemsMart!. 🏪 \u2502\n" +
                "\u2514--------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String despedirse() {
        return "\n\u250c--------------------------------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  ¡Gracias por visitar la Tienda Virtual CheemsMart! Vuelve pronto. 👋 \u2502\n" +
                "\u2514--------------------------------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String mostrarMenu() {
        return "\n\u250c---------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502 1.- Ver Catálogo                \u2502\n" +
                "\u2502 2.- Hacer una compra.           \u2502\n" +
                "\u2502 3.- Cerrar Sesión.              \u2502\n" +
                "\u2502 4.- Salir del Sistema.          \u2502\n" +
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

            System.out.print("c - Cancelar Compra.\nf - Finalizar Compra.\n");
            System.out.print("Escribe el Barcode del Producto que quieres o la letra para elegir la opción: ");
            input = scannerString.nextLine().strip();

            switch (input) {
                case "c": {
                    TiendaFacade.clearConsole();
                    TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                                "\u2502      COMPRA CANCELADA.           \u2502\n" +
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
                                    "\u2502        El Producto No Existe.    \u2502\n" +
                                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                            TiendaFacade.clearConsole();
                            remote.close();
                            continue;
                        }
                        TiendaFacade.clearConsole();
                        TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                                "   Has agregado a tu carrito el producto "   + producto.getNombre() +
                                "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                        TiendaFacade.clearConsole();
                        if (barCodeFinal != null && producto.getBarcode().equals(barCodeFinal)){
                            producto.setPrecio(producto.getPrecio() - (producto.getPrecio() * 0.20));
                        }
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
            System.out.print("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                             "\u2502     INICIANDO COMPRA SEGURA.     \u2502\n" +
                             "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");

            System.out.print("Ingresa tu cuenta bancaría: ");
            String cuentaBancaria = scanner.nextLine().strip();

            // NO COINCIDE.
            if (!cuentaBancaria.equals(user.getBankAccount())){
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502     EL NÚMERO DE CUENTA NO COINCIDE.     \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
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
                        "         No tienes nada en tú catálogo. Vuelve a intentarlo :( " +
                        "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502      COMPRA CANCELADA.           \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;

            }

            if (cargo == 0){

                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                                            "         No tienes el suficiente dinero :( " +
                                            "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502      COMPRA CANCELADA.           \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;
            }

            System.out.print("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                            "\u2502      COMPRA ACEPTADA.           \u2502\n" +
                            "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
            System.out.print(   "\n----------------------------------------".replace('-', '\u2500') + "\n" +
                                "   TÚ CARRITO: " +
                                "\n----------------------------------------".replace('-', '\u2500') + "\n");
            System.out.print(carritoIn.obtenerCarrito());
            System.out.print(   "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                                "       La compra llegará el 10/30/2022"  +
                                "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
            TiendaFacade.sleepFor("",10000);
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
                                    "\u2502           CERRANDO SESIÓN         \u2502\n" +
                                    "\u2514-----------------------------------\u2518".replace('-', '\u2500') + "\n");
        TiendaFacade.clearConsole();
        user = null;
    }

    @Override
    public void mostrarOferta(){

        try {

            // OBJETOS.
            String[] barCodes = {"2345","5467", "1234"};

            Random random = new Random();

            String barcode = barCodes[random.nextInt(barCodes.length)];
            barCodeFinal = barcode;

            Socket socket = new Socket("localhost", 8080);
            Remote remote = new Remote(socket);


            CatalogoProxy catalogoProxy = (CatalogoProxy) remote.receive();



            Producto producto = (Producto) catalogoProxy.getProducto(barcode);

            // HACER DESCUENTO Y MOSTRAR DESCUENTO.
            TiendaFacade.clearConsole();
            TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                    "     Hay un descuento del 20% en el producto " + producto.getNombre() + "\n" +
                    "     Su actual precio es de " + producto.getPrecio() + " y con el descuento será " + (producto.getPrecio() - (producto.getPrecio() * 0.20)) +
                    "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n", 10000);
            TiendaFacade.clearConsole();
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
