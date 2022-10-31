package Productos.DepartamentoElectronica;
import Productos.Producto;

import java.io.Serializable;

public class Smartphone extends Producto implements Serializable {

    public Smartphone(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

}
