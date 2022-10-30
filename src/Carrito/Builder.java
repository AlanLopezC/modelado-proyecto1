package Carrito;

import java.util.LinkedList;

import Productos.Producto;

/**
 * Patrón de Diseño Builder
 * 
 */
public abstract class Builder {
    protected LinkedList<Producto> products = new LinkedList<Producto>();

    /**
     * Agrega un producto a la lista de products.
     * 
     * @param product Componente que se desea agregar.
     * @return Builder Se regresa el constructor.
     */
    public abstract Builder addProduct(Producto product);

    /**
     * Construye el carrito.
     * 
     * @return Carrito Regresa el carrito construido.
     */
    public abstract Carrito build();

    /**
     * Regresa los products.
     * 
     * @return LinkedList<Producto> Regresa la lista de products.
     */
    public LinkedList<Producto> getProducts() {
        return products;
    }

}
