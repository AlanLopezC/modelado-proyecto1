package Productos.DepartamentoAlimentos;

import Productos.Producto;

import java.io.Serializable;

public class Carne extends Producto implements Serializable {

    public Carne(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn){
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

    @Override
    public String mostrarDescuento() {
        return null;
    }

    @Override
    public void hacerDescuento() {

    }
}