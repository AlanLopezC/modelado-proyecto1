package Productos.DepartamentoElectrodomesticos;

import Productos.Producto;

import java.io.Serializable;

public class Mircroondas extends Producto implements Serializable {

    public Mircroondas(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn){
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

}
