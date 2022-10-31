package Productos.DepartamentoElectronica;

import Productos.Producto;

import java.io.Serializable;

public class Laptop extends Producto implements Serializable {

    public Laptop(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn){
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }
}
