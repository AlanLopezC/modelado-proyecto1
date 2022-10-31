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

public class LatinSpanishStore implements Tienda {

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
        return "\u250c---------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502 1.- Ver Catálogo                \u2502\n" +
                "\u2502 2.- Hacer una compra.           \u2502\n" +
                "\u2502 3.- Cerrar Sesión.              \u2502\n" +
                "\u2502 4.- Salir del Sistema.          \u2502\n" +
                "\u2514---------------------------------\u2518".replace('-', '\u2500') + "\n"
                + "\nElección: ";
    }

    @Override
    public void realizarCompra(CatalogoProxy catalogoProxyIn, User user){

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
                case "c": System.out.print("Cancelar Compra");
                    return;
                case "f": compraSegura(carritoBuilder.build(), user);
                default:
                    Producto producto = (Producto) catalogoProxyIn.getProducto(input);
                    if (producto == null){
                        System.out.print("Este producto no existe");
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

            System.out.println("***OBTENIDO CUENTA ...***");

            System.out.print("Ingresa tu cuenta bancaría: ");
            String cuentaBancaria = scanner.nextLine().strip();
            if (!cuentaBancaria.equals(user.getBankAccount())){
                System.out.print("El número de cuenta Bancaria no coincide.");
                return;
            }


            Double cargo = cuentaBancariaProxy.retirar(carritoIn.costoTotal());

            if (cargo == 0){
                System.out.print("Compra cancelada.");
                remote.close();
                return;
            }

            System.out.print("\nTu carrito:\n" + carritoIn.obtenerCarrito());
            System.out.print("La compra llegará el 10/30/2022");
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
                                    "\u2502           CERRANDO SESIÓN         \u2502\n" +
                                    "\u2514-----------------------------------\u2518".replace('-', '\u2500') + "\n");
        TiendaFacade.clearConsole();
        user = null;
    }

    @Override
    public void mostrarOferta(CatalogoProxy catalogoProxy){

    }
}
