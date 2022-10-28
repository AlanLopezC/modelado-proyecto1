package Pruductos;
import Catalogo.Productos;
import java.io.Serializable;

public class Fruta extends Producto implements Serializable{

    public Fruta(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

    @Override
    public String mostrarDescuento() {
        return "Hay un descuento del 20%, el producto vale " + (getPrecio() - (getPrecio() * 0.20));
    }

    @Override
    public void hacerDescuento() {
        setPrecio(getPrecio() - (getPrecio() * 0.20));
    }
}
