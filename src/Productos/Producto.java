package Productos;

import java.util.Map;
import java.util.Iterator;
import Catalogo.*;
import java.util.HashMap;
import java.io.Serializable;

public abstract class Producto extends Productos implements Serializable {

    // NOMBRE
    private final String nombre;

    // CÓDIGO DE BARRAS
    private final String barcode;

    // DEPARTAMENTO
    private final String departamento;

    // PRECIO
    private double precio;

    // DESCRIPCIÓN
    private String description;

    // PROPIEDADES
    private Map<String, Object> propiedades;

    /**
     * Método para crear un objeto Producto.
     * @param nombreIn - Nombre del Producto.
     * @param barcodeIn - Barcode
     * @param departamentoIn - Departamento del Producto.
     * @param precioIn - Precio del Producto.
     * @param descriptionIn - Descripción del Producto.
     */
    public Producto(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {

        nombre = nombreIn;
        barcode = barcodeIn;
        departamento = departamentoIn;
        precio = precioIn;
        description = descriptionIn;
        propiedades = new HashMap<>();

    }

    // MÉTODOS GETTERS

    /**
     * Método para obtener el Nombre del Producto.
     * @return String - Nombre del Producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Método para obtener el barcode del Producto.
     * @return String - Barcode del Producto.
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Método para obtener el Departamento del Producto.
     * @return String - Departamento del Producto.
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * Método para obtener el Precio del Producto.
     * @return Double - Precio del Producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Método para obtener Descripción del Producto.
     * @return String - Descripción del Producto.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Método para obtener las Propiedades del Producto.
     * @return String - Propiedades del Producto.
     */
    public String getPropiedades() {

        StringBuilder propiedadesTexto = new StringBuilder();

        for (String propiedad : propiedades.keySet()) {
            propiedadesTexto.append("\n").append(propiedad).append(" -> ").append(propiedades.get(propiedad));
        }
        return propiedadesTexto.toString();
    }

    // MÉTODOS SETTERS

    /**
     * Método para poner el precio del Producto.
     * @param precio - Precio del Producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Método para obtener la Descripción del Producto.
     * @param description - Descripción del Producto.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    // AGREGAR PROPIEDADES

    /**
     * Método para agregar propiedad al producto.
     * @param propiedad - Propiedad del Producto.
     * @param value - Valor del Producto.
     */
    public void agregarPropiedad(String propiedad, Object value) {
        propiedades.put(propiedad, value);
    }

    // TO STRING
    @Override
    public String toString() {
        return "Nombre: " + nombre + "\n" +
                "Precio: " + precio + "\n" +
                "BarCode: " + barcode + "\n" +
                "\n\nDescripción:\n" + description +
                "\n\nPropiedades:" + getPropiedades();
    }

    @Override
    public void printDatos(){
        System.out.print("\n-----------".replace('-', '\u2500') + "\n" +
                            this +
                        "\n-----------".replace('-', '\u2500') + "\n"
        );
    }
    
    public Iterator crearIterador(){
        return new NullIterador();
    }
}
