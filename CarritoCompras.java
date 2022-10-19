import java.util.List;
import java.util.LinkedList;

public class CarritoCompras {

    private final List<Producto> productos = new LinkedList<>();

    public void agregarProducto(Producto productoIn){
        productos.add(productoIn);
    }

    public boolean eliminarProducto(Producto productoIn){
        return productos.remove(productoIn);
    }

    public double generarTotal(){

        double costo = 0;
        for ( Producto producto : productos) {
            costo += producto.getPrecio();
        }
        return costo;
    }

    public StringBuilder obtenerCarrito(){

        StringBuilder carrito = new StringBuilder();

        for (Producto producto : productos){
            carrito.append("\n--------------------------------------\n");
            carrito.append(producto.toString());
            carrito.append("\n--------------------------------------\n");
        }
        return carrito;
    }
}
