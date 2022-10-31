package Productos.DepartamentoElectrodomesticos;

import Productos.Producto;

import java.io.Serializable;

public class Lavadora extends Producto implements Serializable {

    public Lavadora(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn){
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }
}
