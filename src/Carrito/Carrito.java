package Carrito;

import Productos.Producto;

import java.util.LinkedList;

public class  Carrito {

    private LinkedList<Producto> productos;

    public Carrito(Builder builder) {
        this.productos = builder.getProducts();
    }

    /**
     * Método para obtener el costo total del carrito.
     * @return Double - Costo Total.
     */
    public double costoTotal() {

        double costo = 0;
        for (Producto producto : productos) {
            costo += producto.getPrecio();
        }
        return costo;
    }

    /**
     * Método para obtener los productos del carrito.
     * @return StringBuilder - Productos del Carrito.
     */
    public StringBuilder obtenerCarrito() {

        StringBuilder carrito = new StringBuilder();

        for (Producto producto : productos) {
            carrito.append("\n--------------------------------------\n");
            carrito.append(producto.toString());
            carrito.append("\n--------------------------------------\n");
        }
        return carrito;
    }
}
