package Catalogo;
import Pruductos.*;
import java.util.*;
import java.io.Serializable;
/**
* Clase que simula el catálogo de la tienda virtual.
 **/
public class Catalogo implements Serializable, CatalogoServer{
    /*
    atributo en el cual se agregará el catálogo con los departamentos y sus correspondientes artículos.
    */
    private Productos catalogo;
    /** 
    * Constructor de la clase.
    **/
    public Catalogo(){
        Producto iPhone = new Smartphone("Iphone 3", "12345", "Celulares", 7999.00,
                "- El producto tendrá una batería que supere el 80 % de capacidad en comparación con uno totalmente nuevo.\n"
                        +
                        "- Es posible que los accesorios que vengan con el producto no sean originales, pero serán compatibles y totalmente funcionales.\n"
                        +
                        "- Es posible que el producto venga en una caja genérica y no en la caja original.\n" +
                        "- 64GB\n" +
                        "- 4GB memoria RAM\n" +
                        "- Reacondicionado Tipo A\n" +
                        "- Caja Genérica\n" +
                        "- Producto Reacondicionado (Por manejo e importación puede presentar detalles estéticos que no afecten el funcionamiento");

        iPhone.agregarPropiedad("Color", "Negro");
        iPhone.agregarPropiedad("Procesador", "A15");
        iPhone.agregarPropiedad("Seguridad", "Face ID");

        Producto samsung = new Smartphone("Samsung", "4353", "Electronica", 1000.00,
                "- El producto está reacondicionado, es totalmente funcional y está en excelentes condiciones. Respaldado por la garantía de 90 días de Amazon Renewed.\n"
                        +
                        "- Los proveedores cualificados de Amazon han inspeccionado, probado y limpiado profesionalmente este producto usado.\n"
                        +
                        "- Este producto está en \"Condición excelente\". No muestra signos de daños cosméticos visibles desde una distancia de 30 centímetros.\n"
                        +
                        "- Los productos con baterías superarán el 80 % de capacidad en comparación con uno nuevo. Es posible que los accesorios no sean originales, pero serán compatibles y totalmente operativos. Es posible que el producto venga en una caja genérica.\n"
                        +
                        "- Este producto viene con una garantía de 90 días respaldada por el proveedor.");

        samsung.agregarPropiedad("Color", "Rojo");
        samsung.agregarPropiedad("Procesador", "Snapdragon");
        samsung.agregarPropiedad("Seguridad", "Lector de Huellas");

        Producto manzana = new Fruta("Manzana", "1425566", "Alimentos", 10, "- Traído de las granjas.");
        manzana.agregarPropiedad("Color", "Rojo");
        manzana.agregarPropiedad("Tiene gusanos", false);
        
        ArrayList<Productos> frutas = new ArrayList<Productos>();
        ArrayList<Productos> smarts = new ArrayList<Productos>();
        ArrayList<Productos> allDeparments = new ArrayList<Productos>();
        Productos departamentoFrutas = new ProductosDepartamento(frutas, "Frutas","1");
        Productos departamentoSmart = new ProductosDepartamento(smarts, "Tecnología","1");
        Productos catalogo = new ProductosDepartamento(allDeparments, "Catálogo","1");
        departamentoFrutas.add(manzana);
        departamentoSmart.add(iPhone);
        departamentoSmart.add(samsung);
        catalogo.add(departamentoFrutas);
        catalogo.add(departamentoSmart);
        this.catalogo = catalogo;
        

    }
    /*
    * Método que imprime el catálogo. 
    */
    public void mostrarCatalogo(){ 
        catalogo.printDatos(); 
    }
    /**
    * Método que devuelve un producto dado un código de barras.
    * @return Productos 
    * @param bc Código de barras del producto a elegir. 
    **/
    public Productos getProducto(String bc){
        Iterator it = catalogo.crearIterador();
        while(it.hasNext()){
            Productos producto = (Productos)it.next();
            if(producto.getBarcode().equals(bc)){
                return producto;
            }
        }
        return null;
    }
    public static void main(String[] args){
        Catalogo catprueba = new Catalogo();
        //catprueba.mostrarCatalogo();
        System.out.println(catprueba.getProducto("4353"));
    }


   
}
 