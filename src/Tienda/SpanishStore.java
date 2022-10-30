package Tienda;

import Carrito.Carrito;
import Carrito.CarritoBuilder;
import Catalogo.CatalogoProxy;
import Cuenta.CuentaProxy;
import Productos.Producto;
import Remote.Remote;
import Users.User;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SpanishStore implements Tienda {

    @Override
    public String saludar() {
        return "\n\u250c-----------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  ¡Bienvenido a Vuestra Tienda Virtal CheemsMart!. 🏪 \u2502\n" +
                "\u2514-----------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String despedirse() {
        return "\n\u250c--------------------------------------------------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502  Os agradezco por venid a vuestra Tienda Virtual CheemsMart. Volved Pronto 👋 \u2502\n" +
                "\u2514--------------------------------------------------------------------------\u2518".replace('-', '\u2500') + "\n";
    }

    @Override
    public String mostrarMenu() {
        return "\n\u250c---------------------------------\u2510".replace('-', '\u2500') + "\n" +
                "\u2502 1.- Visualizad el Catálogo.                \u2502\n" +
                "\u2502 2.- Haced una compra.            \u2502\n" +
                "\u2502 3.- Cerrad vuestra cuenta.                   \u2502\n" +
                "\u2502 4.- Salid del Sistema.      \u2502\n" +
                "\u2514---------------------------------\u2518".replace('-', '\u2500') + "\n"
                + "\nChoice: ";
    }

    @Override
    public void realizarCompra(CatalogoProxy catalogoProxyIn, User user){

        // OBJETOS
        CarritoBuilder carritoBuilder = new CarritoBuilder();
        Scanner scannerString = new Scanner(System.in);

        // VARIABLES
        String input;


        while (true) {

            mostrarCatalogo();
            System.out.print("c - Cancelad vuestra Compra.\nf - Terminad vuestra Compra.\n");
            System.out.print("Poned el barcode del producto que queréis o la letra que queréis: ");
            input = scannerString.nextLine().strip();

            switch (input) {
                case "c": System.out.print("Cancelad vuestra Compra.");
                    return;
                case "f": compraSegura(carritoBuilder.build(), user);
                default:
                    Producto producto = (Producto) catalogoProxyIn.getProducto(input);
                    if (producto == null){
                        System.out.print("El producto no está en vuestra tienda virtual.");
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

            System.out.print("Ingresa vuestra cuenta bancaría: ");
            String cuentaBancaria = scanner.nextLine().strip();
            if (!cuentaBancaria.equals(user.getBankAccount())){
                System.out.print("Vuestro número de cuenta Bancaria no coincide.");
                return;
            }


            Double cargo = cuentaBancariaProxy.retirar(carritoIn.costoTotal());

            if (cargo == 0){
                System.out.print("Vuestra compra está cancelada.");
                remote.close();
                return;
            }

            System.out.print("\nYour cart:\n" + carritoIn.obtenerCarrito());
            System.out.print("Vuestra compra llegará el 10/30/2022");
            remote.send(cuentaBancariaProxy);
            remote.close();
        }
        catch (IOException e){
            System.out.print(e);
        }

    }

    @Override
    public void salirCerrarSesion(User user) {
        System.out.print("\nCerrando Sesión.\n");
        user = null;

    }

    @Override
    public String salirSistema() {
        return "Saliendo de vuestro sistema.";
    }

    @Override
    public void mostrarOferta(CatalogoProxy catalogoProxy){

    }
}
