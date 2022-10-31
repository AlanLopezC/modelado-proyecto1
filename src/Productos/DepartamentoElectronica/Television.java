package Productos.DepartamentoElectronica;

import Productos.Producto;

import java.io.Serializable;

public class Television extends Producto implements Serializable {

    public Television(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn){
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }
}
