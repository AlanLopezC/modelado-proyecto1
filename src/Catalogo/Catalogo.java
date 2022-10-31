package Catalogo;
import Productos.*;
import Productos.DepartamentoAlimentos.Fruta;
import Productos.DepartamentoElectrodomesticos.Lavadora;
import Productos.DepartamentoElectronica.Smartphone;

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


        // DEPARTAMENTO ALIMENTOS.
        Producto manzana = new Fruta("Manzana", "2345", "Alimentos", 10, "- Traído de las Granjas del Abuelo Toño");
        manzana.agregarPropiedad("Color", "Rojo");

        // DEPARTAMENTO ELECTRODOMÉSTICOS
        Producto lavadoraWhirlpool = new Lavadora("Lavadora Whirlpool", "5345", "Electrodomesticos", 8990, "- 12 Ciclos de lavado, (3 ciclos Xpert: Ropa de Color)" );
        lavadoraWhirlpool.agregarPropiedad("Peso", 20);


        // DEPARTAMENTO ELECTRONICA.
        Producto iPhone = new Smartphone("Iphone 15", "3425", "Electronica", 7999, "- El producto tendrá una batería que supere el 80% de capacidad en comparación con uno totalmente nuevo.\n"
                                                                                                                                  + "- Es posible que los accesorios que vengan con el producto no sean originales, pero serán compatibles y totalmente funcionales");
        iPhone.agregarPropiedad("Color", "Negro");




        // LISTA FRUTAS
        ArrayList<Productos> frutas = new ArrayList<>();

        // DEPARTAMENTO CARNE
        ArrayList<Productos> carnes = new ArrayList<>();

        // DEPARTAMENTO LACTEOS
        ArrayList<Productos> lacteos = new ArrayList<>();

        // LISTA LAVADORAS
        ArrayList<Productos> lavadoras = new ArrayList<>();

        // DEPARTAMENTO MICROONDAS
        ArrayList<Productos> microondas = new ArrayList<>();

        // DEPARTAMENTO REFRIGERADOR
        ArrayList<Productos> refrigeradores = new ArrayList<>();

        // LISTA SMARTPHONES
        ArrayList<Productos> smartPhones = new ArrayList<>();

        // DEPARTAMENTO LAPTOPS
        ArrayList<Productos> laptops = new ArrayList<>();

        // DEPARTAMENTO TELEVISIÓN
        ArrayList<Productos> televisiones = new ArrayList<>();



        // TODOS LOS DEPARTAMENTOS
        ArrayList<Productos> allDeparments = new ArrayList<>();

        // DEPARTAMENTO FRUTAS
        Productos departamentoFrutas = new ProductosDepartamento(frutas, "Frutas","1");

        // DEPARTAMENTO LAVADORAS
        Productos departamentoLavadoras = new ProductosDepartamento(lavadoras, "Lavadoras", "1");

        // DEPARTAMENTO SMARTPHONES
        Productos departamentoSmartPhones = new ProductosDepartamento(smartPhones, "SmartPhones","1");

        // CATÁLOGO
        Productos catalogo = new ProductosDepartamento(allDeparments, "Catálogo","1");

        // AGREGANDO PRODUCTOS A SUS RESPECTIVOS DEPARTAMENTOS

        // AGREGANDO FRUTAS
        departamentoFrutas.add(manzana);

        // AGREGANDO SMARTPHONES
        departamentoSmartPhones.add(iPhone);

        // AGREGANDO LAVADORAS
        departamentoLavadoras.add(lavadoraWhirlpool);


        // AGREGANDO LOS DEPARTAMENTOS A SU CATÁLOGO

        // DEPARTAMENTO FRUTAS
        catalogo.add(departamentoFrutas);

        // DEPARTAMENTO SMARTPHONES
        catalogo.add(departamentoSmartPhones);

        // DEPARTAMENTO LAVADORAS
        catalogo.add(departamentoLavadoras);


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
 