package Catalogo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

/** 
* Clase que simula el comportamiento de un departamento de productos. 
**/

public class ProductosDepartamento extends Productos implements Serializable {
    /*
    * Lista de productos del departamento.
    */
    private ArrayList<Productos> productos = new ArrayList<Productos>();

    /*
    * Nombre del departamento.
    */
    private String nombre;

    /*
    * Código de barras del departamento.
    */
    private String idDepartamento; 
    /** 
    * Constructor de la clase.
    * @param productos lista de los productos del departamento.
    * @param nombre Nombre del departamento.
    * @param id identificador del departamento. 

    **/
    public ProductosDepartamento(ArrayList<Productos> productos, String nombre, String id){
        this.productos = productos;
        this.nombre = nombre;
        idDepartamento = id;


    }
    /**
    * Método que agrega un producto al departamento.
    * @param producto producto a agregar al departamento. 
     **/
    @Override
    public void add(Productos producto){
        productos.add(producto);
    }
    /** 
    * Método que elimina un producto del departamento.
    * @param producto producto a remover. 
    **/
    @Override
    public void remove(Productos producto){
        productos.remove(producto);
    }
    /** 
    * Método que devuelve el producto en la posición i-ésima.
    * @param i posición del producto en la lista de productos del  departamento.
    * @return Productos producto en la i-ésima posición.
    **/
    public Productos getProducto(int i){
        return productos.get(i);
    }

    /** 
    * Método que imprime la información (los datos) del departamento.
    **/
    @Override
    public void printDatos(){
        System.out.print("\n\u250c----------------------------------\u2510".replace('-', '\u2500') + "\n");
        System.out.print("     " + this.getNombre());
        System.out.print("\n\u2514----------------------------------\u2518".replace('-', '\u2500') + "\n");
            Iterator<Productos> it = productos.iterator();
            while(it.hasNext()){
                Productos producto = (Productos)it.next();
                producto.printDatos();
            }
    }
    /** 
    * Método que devuelve el nombre del departamento.
    * @return  String 
    **/
    @Override
    public String getNombre(){
        return nombre;
    }
    /** 
    * Método que devuelve el código de barras del departamento.
    * @return  String 
    **/
    @Override
    public String getBarcode(){
        return idDepartamento;
    }
    /** 
    * Método que devuelve la lista de productos del departamento.
    * @return  ArrayList<Productos> 
    **/
    @Override
    public ArrayList<Productos> getProductos(){
        return productos;
    }
    /** 
    * Método que crea el iterador de la clase.
    * @return Iterator
    **/
    public Iterator crearIterador(){
        return new IteradorComposite(productos.iterator());
    }
}