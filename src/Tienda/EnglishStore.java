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

public class EnglishStore implements Tienda {

    private String barCodeFinal; // BARCODE DEL PRODUCTO CON DESCUENTO.

    @Override
    public String saludar() {
        return "\n\u250c-----------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  Welcome to the CheemsMart Virtual Store!. 🏪 \u2502\n" +
                "\u2514-----------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String despedirse() {
        return "\n\u250c--------------------------------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  Thank you for visiting the CheemsMart Virtual Store! Come back soon. 👋 \u2502\n" +
                "\u2514--------------------------------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String mostrarMenu() {
        return  "\n\u250c---------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502 1.- See Catalog.                \u2502\n" +
                "\u2502 2.- Make a Purchase.            \u2502\n" +
                "\u2502 3.- Sign off.                   \u2502\n" +
                "\u2502 4.- Get out of the system.      \u2502\n" +
                "\u2514---------------------------------\u2518".replace('-', '\u2500') + "\n"
                + "\nChoice: ";
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

            System.out.print("c - Cancel Purchase.\nf - Finish Purchase.\n");
            System.out.print("Put the Barcode of the product you want or the letter to choose an option: ");
            input = scannerString.nextLine().strip();

            switch (input) {
                case "c": {
                    TiendaFacade.clearConsole();
                    TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                            "\u2502      Purchase cancelled.        \u2502\n" +
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
                                    "\u2502    The product does not exist.    \u2502\n" +
                                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                            TiendaFacade.clearConsole();
                            remote.close();
                            continue;
                        }
                        TiendaFacade.clearConsole();
                        TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                                "   You have added the " + producto.getNombre()  + " product to your cart "  +
                                "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                        TiendaFacade.clearConsole();
                        if (barCodeFinal != null && producto.getBarcode().equals(barCodeFinal)){
                            producto.setPrecio(producto.getPrecio() - (producto.getPrecio() * 0.40));
                        }
                        carritoBuilder.addProduct(producto);


                        remote.close();

                    }catch (IOException e){
                        TiendaFacade.clearConsole();
                        TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                "\u2502      SERVER DOWN OR NOT INITIALIZED.  \u2502\n" +
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
                    "\u2502     STARTING SECURE PURCHASE.    \u2502\n" +
                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");

            System.out.print("Enter your bank account: ");
            String cuentaBancaria = scanner.nextLine().strip();

            // NO COINCIDE.
            if (!cuentaBancaria.equals(user.getBankAccount())){
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502     THE ACCOUNT NUMBER DOES NOT MATCH.     \u2502\n" +
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
                        "         You have nothing in your cart. Retry :( " +
                        "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502      PURCHASE CANCELLED.         \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;

            }

            if (cargo == 0){

                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                        "         You do not have enough money :( " +
                        "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();
                TiendaFacade.sleepFor("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                        "\u2502      PURCHASE CANCELLED.        \u2502\n" +
                        "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
                TiendaFacade.clearConsole();

                remote.send(cuentaBancariaProxy);
                remote.close();
                return;
            }

            System.out.print("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      PURCHASE ACCEPTED.           \u2502\n" +
                    "\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
            System.out.print(   "\n----------------------------------------".replace('-', '\u2500') + "\n" +
                    "   YOUR CART: " +
                    "\n----------------------------------------".replace('-', '\u2500') + "\n");
            System.out.print(carritoIn.obtenerCarrito());
            System.out.print(   "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n" +
                    "       The purchase will arrive on 10/30/2022"  +
                    "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n");
            TiendaFacade.sleepFor("", 10000);
            TiendaFacade.clearConsole();

            remote.send(cuentaBancariaProxy); // ACTUALIZANDO LA CUENTA BANCARIA.
            remote.close();
        }
        catch (IOException e){
            TiendaFacade.clearConsole();
            TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      SERVER DOWN OR NOT INITIALIZED.  \u2502\n" +
                    "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
            TiendaFacade.clearConsole();
        }
    }

    @Override
    public void salirCerrarSesion(User user) {
        TiendaFacade.clearConsole();
        TiendaFacade.sleepFor("\n\u250c-----------------------------------\u2510".replace('-', '\u2500') + "\n" +
                                    "\u2502           SIGN OFF                \u2502\n" +
                                    "\u2514-----------------------------------\u2518".replace('-', '\u2500') + "\n");
        TiendaFacade.clearConsole();
        user = null;
    }

    @Override
    public void mostrarOferta(){

        try {

            // OBJETOS.
            String[] barCodes = {"3425","7486", "4633"};

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
                    "     There is a 40% discount on the " + producto.getNombre() + " product.\n" +
                    "     Its current price is " + producto.getPrecio() + " and with the discount it is " + (producto.getPrecio() - (producto.getPrecio() * 0.40)) +
                    "\n--------------------------------------------------------------------".replace('-', '\u2500') + "\n", 10000);
            TiendaFacade.clearConsole();
            remote.close();

        }catch (IOException e){
            TiendaFacade.clearConsole();
            TiendaFacade.sleepFor("\n\u250c-----------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                    "\u2502      SERVER DOWN OR NOT INITIALIZED.  \u2502\n" +
                    "\u2514-----------------------------------------\u2518".replace('-', '\u2500') + "\n");
            TiendaFacade.clearConsole();
        }
    }

}
