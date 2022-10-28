package Catalogo;
import java.util.ArrayList;
import java.util.Iterator;
public class ProductosDepartamento extends Productos{
    private ArrayList<Productos> productos = new ArrayList<Productos>();
    private String nombre;
    private String idDepartamento; 

    public ProductosDepartamento(ArrayList<Productos> productos, String nombre, String id){
        this.productos = productos;
        this.nombre = nombre;
        idDepartamento = id;


    }
    @Override
    public void add(Productos producto){
        productos.add(producto);
    }
    @Override
    public void remove(Productos producto){
        productos.remove(producto);
    }

    public Productos getProducto(int i){
        return productos.get(i);
    }

    @Override
    public void printDatos(){
        System.out.println("\n"+ this.getNombre());
        System.out.println("-----------------------------");
            Iterator<Productos> it = productos.iterator();
            while(it.hasNext()){
                Productos producto = (Productos)it.next();
                producto.printDatos();
            }
    }
    @Override
    public String getNombre(){
        return nombre;
    }
    @Override
    public String getBarcode(){
        return idDepartamento;
    }
    @Override
    public ArrayList<Productos> getProductos(){
        return productos;
    }

    public Iterator crearIterador(){
        return new IteradorComposite(productos.iterator());
    }
}