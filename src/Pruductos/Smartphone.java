package Pruductos;

public class Smartphone extends Producto {

    public Smartphone(String nombreIn, String barcodeIn, String departamentoIn, double precioIn, String descriptionIn) {
        super(nombreIn, barcodeIn, departamentoIn, precioIn, descriptionIn);
    }

    @Override
    public String mostrarDescuento() {
        return "Hay un descuento del 40%, el producto vale " + (getPrecio() - (getPrecio() * 0.40));
    }

    @Override
    public void hacerDescuento() {
        setPrecio(getPrecio() - (getPrecio() * 0.40));
    }
}
