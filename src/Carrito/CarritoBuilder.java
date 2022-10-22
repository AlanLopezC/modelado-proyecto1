package Carrito;

import Pruductos.Producto;

public class CarritoBuilder extends Builder {

    /**
     * Construye el carrito.
     * 
     * @return Carrito Regresa la nave construida.
     */
    @Override
    public Carrito build() {
        return new Carrito(this);
    }

    /**
     * Agrega un componente a la lista de componentes.
     * 
     * @param component Componente que se desea agregar.
     * @return Builder Se regresa el constructor.
     */
    @Override
    public Builder addProduct(Producto product) {
        products.add(product);
        return this;
    }

}