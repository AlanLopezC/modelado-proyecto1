package Pruductos;

import java.util.Map;

import Others.Descuento;

import java.util.HashMap;
import java.io.Serializable;

public abstract class Producto implements Descuento, Serializable {

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

    public Producto(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {

        nombre = nombreIn;
        barcode = barcodeIn;
        departamento = departamentoIn;
        precio = precioIn;
        description = descriptionIn;
        propiedades = new HashMap<>();

    }

    // MÉTODOS GETTERS

    public String getNombre() {
        return nombre;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getDepartamento() {
        return departamento;
    }

    public double getPrecio() {
        return precio;
    }

    public String getDescription() {
        return description;
    }

    public String getPropiedades() {

        StringBuilder propiedadesTexto = new StringBuilder();

        for (String propiedad : propiedades.keySet()) {
            propiedadesTexto.append("\n").append(propiedad).append(" : ").append(propiedades.get(propiedad));
        }
        return propiedadesTexto.toString();
    }

    // MÉTODOS SETTERS

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // AGREGAR PROPIEDADES
    public void agregarPropiedad(String propiedad, Object value) {
        propiedades.put(propiedad, value);
    }

    // TO STRING
    @Override
    public String toString() {
        return nombre + " : $" + precio + "\n\nDescripción:\n" + description + "\n\nPropiedades:" + getPropiedades();
    }

}
