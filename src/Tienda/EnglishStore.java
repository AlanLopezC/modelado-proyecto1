package Tienda;

import Carrito.Carrito;
import Carrito.CarritoBuilder;
import Catalogo.CatalogoProxy;
import Catalogo.Productos;
import Cuenta.CuentaProxy;
import Productos.Producto;
import Remote.Remote;
import TiendaFacade.Design;
import TiendaFacade.TiendaFacade;
import Users.User;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class EnglishStore implements Tienda {

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
    public void realizarCompra(CatalogoProxy catalogoProxyIn, User user) {

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
                case "c": System.out.print("Canceled Purchase.");
                    return;
                case "f": compraSegura(carritoBuilder.build(), user);
                default:
                    Producto producto = (Producto) catalogoProxyIn.getProducto(input);
                    if (producto == null){
                        System.out.print("This product does not exist.");
                        continue;
                    }
                    carritoBuilder.addProduct(producto);
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

            System.out.println("***Getting account ...***");

            System.out.print("Enter your bank accout: ");
            String cuentaBancaria = scanner.nextLine().strip();
            if (!cuentaBancaria.equals(user.getBankAccount())){
                System.out.print("Account number does not match.");
                return;
            }


            Double cargo = cuentaBancariaProxy.retirar(carritoIn.costoTotal());

            if (cargo == 0){
                System.out.print("Canceled Purchase.");
                remote.close();
                return;
            }

            System.out.print("\nYour cart:\n" + carritoIn.obtenerCarrito());
            System.out.print("The purchase will arrive on 10/30/2022");
            remote.send(cuentaBancariaProxy);
            remote.close();
        }
        catch (IOException e){
            System.out.print(e);
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
    public void mostrarOferta(CatalogoProxy catalogoProxyIn){

        ArrayList<Productos> departamentoElectronica = catalogoProxyIn.getProducto("Electronica").getProductos();
        Random random = new Random();

        int index = random.nextInt(departamentoElectronica.size());
        Producto producto = (Producto) departamentoElectronica.get(index);
        producto.hacerDescuento();



        System.out.print("The system has found an offer.");
        System.out.print(producto);

    }
}
