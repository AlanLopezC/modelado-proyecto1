package Productos.DepartamentoAlimentos;
import Productos.Producto;

import java.io.Serializable;

public class Fruta extends Producto implements Serializable{

    public Fruta(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

}
