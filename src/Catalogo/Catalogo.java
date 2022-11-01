package Catalogo;
import Productos.*;
import Productos.DepartamentoAlimentos.Carne;
import Productos.DepartamentoAlimentos.Fruta;
import Productos.DepartamentoAlimentos.Lacteos;
import Productos.DepartamentoElectrodomesticos.Lavadora;
import Productos.DepartamentoElectrodomesticos.Mircroondas;
import Productos.DepartamentoElectrodomesticos.Refrigerador;
import Productos.DepartamentoElectronica.Laptop;
import Productos.DepartamentoElectronica.Smartphone;
import Productos.DepartamentoElectronica.Television;


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

        Producto carneRes = new Carne("Carne de de Res", "5467", "Alimentos", 50, "- Las vacas no han sido lastimadas.\n" +
                                                                                                                            "- Se debe de conservar en el refrigerador.");
        carneRes.agregarPropiedad("Peso", 0.5);

        Producto lecheSantaClara = new Lacteos("Leche Santa Clara", "1234", "Alimentos", 100, "- Santa clara trae a tu hogar leche entera y 100% pura de vaca");
        lecheSantaClara.agregarPropiedad("Vitamina", "D");
        lecheSantaClara.agregarPropiedad("Vitamina", "A");


        // DEPARTAMENTO ELECTRODOMÉSTICOS
        Producto lavadoraWhirlpool = new Lavadora("Lavadora Whirlpool", "5345", "Electrodomesticos", 8990, "- 12 Ciclos de lavado, (3 ciclos Xpert: Ropa de Color)" );
        lavadoraWhirlpool.agregarPropiedad("Peso", 20);

        Producto microondasSamsung = new Mircroondas("Microondas Samsung", "9762", "Electrodomesticos", 3190, "- Su moderno diseño en negro con gris combina con el estilo de tu cocina.\n" +
                                                                                                                                                             "- Su alta tecnología te ofrece un calentamiento uniforme");
        microondasSamsung.agregarPropiedad("Niveles de Potencia", 10);
        microondasSamsung.agregarPropiedad("Permite descongelar y hervir", true);

        Producto refrigeradorMabe = new Refrigerador("Refrigerador Mabe", "2843", "Electrodomesticos", 12990, "- Conserva la frescura de tus alimentos de manera sencilla.\n" +
                                                                                                                                                            "- Diseño Moderno.");
        refrigeradorMabe.agregarPropiedad("Número de Parrillas", 3);
        refrigeradorMabe.agregarPropiedad("Cristal Templado", true);


        // DEPARTAMENTO ELECTRONICA.
        Producto iPhone = new Smartphone("Iphone 15", "3425", "Electronica", 7999, "- El producto tendrá una batería que supere el 80% de capacidad en comparación con uno totalmente nuevo.\n"
                                                                                                                                  + "- Es posible que los accesorios que vengan con el producto no sean originales, pero serán compatibles y totalmente funcionales");
        iPhone.agregarPropiedad("Color", "Negro");

        Producto hPVictus = new Laptop("HP Victus", "7486", "Electronica", 20000, "- Increíble para diseñadores.");
        hPVictus.agregarPropiedad("Batería", "9H");
        hPVictus.agregarPropiedad("Peso", 2);
        hPVictus.agregarPropiedad("Procesador", "i5");
        hPVictus.agregarPropiedad("SSD", "256");

        Producto hisenseTV = new Television("TV Hisense", "4633", "Electronica", 8999, "- Ahora ve tus programas favoritos con mayor tecnología.\n" +
                                                                                                                                      "- Analiza las condiciones de luz y ajusta el brillo de la pantalla");
        hisenseTV.agregarPropiedad("Alto (cm)", 71.1);
        hisenseTV.agregarPropiedad("Ancho (cm)", 122.7);
        hisenseTV.agregarPropiedad("Peso", 18.10);



        // LISTA ALIMENTOS
        ArrayList<Productos> alimentos = new ArrayList<>();

        // LISTA FRUTAS
        ArrayList<Productos> frutas = new ArrayList<>();

        // LISTA CARNE
        ArrayList<Productos> carnes = new ArrayList<>();

        // LISTA LACTEOS
        ArrayList<Productos> lacteos = new ArrayList<>();



        // LISTA ELECTRODOMESTICOS
        ArrayList<Productos> electrodomesticos = new ArrayList<>();

        // LISTA LAVADORAS
        ArrayList<Productos> lavadoras = new ArrayList<>();

        // LISTA MICROONDAS
        ArrayList<Productos> microondas = new ArrayList<>();

        // LISTA REFRIGERADOR
        ArrayList<Productos> refrigeradores = new ArrayList<>();



        // LISTA ELECTRONICA
        ArrayList<Productos> electronica = new ArrayList<>();

        // LISTA SMARTPHONES
        ArrayList<Productos> smartPhones = new ArrayList<>();

        // LISTA LAPTOPS
        ArrayList<Productos> laptops = new ArrayList<>();

        // LISTA TELEVISIÓN
        ArrayList<Productos> televisiones = new ArrayList<>();



        // TODOS LOS DEPARTAMENTOS
        ArrayList<Productos> allDeparments = new ArrayList<>();




        // DEPARTAMENTO ALIMENTOS
        Productos departamentoAlimentos = new ProductosDepartamento(alimentos, "Alimentos", "1");

        // DEPARTAMENTO FRUTAS
        Productos departamentoFrutas = new ProductosDepartamento(frutas, "Frutas","1.1");

        // DEPARTAMENTO CARNES
        Productos departamentoCarnes = new ProductosDepartamento(carnes, "Carnes", "1.2");

        // DEPARTAMENTO LACTEOS
        Productos departamentoLacteos = new ProductosDepartamento(lacteos, "Lacteos", "1.3");




        // DEPARTAMENTO ELECTRODOMESTICOS
        Productos departamentoElectrodomesticos = new ProductosDepartamento(electrodomesticos, "Electrodomesticos", "2");

        // DEPARTAMENTO LAVADORAS
        Productos departamentoLavadoras = new ProductosDepartamento(lavadoras, "Lavadoras", "2.1");

        // DEPARTAMENTO MICROONDAS
        Productos departamentoMicroondas = new ProductosDepartamento(microondas, "Microondas", "2.2");

        // DEPARTAMENTO REFRIGERADORES
        Productos departamentoRefrigeradores = new ProductosDepartamento(refrigeradores, "Refrigeradores", "2.3");





        // DEPARTAMENTO ELECTRONICA
        Productos departamentoElectronica = new ProductosDepartamento(electronica, "Electronica", "3");

        // DEPARTAMENTO SMARTPHONES
        Productos departamentoSmartPhones = new ProductosDepartamento(smartPhones, "SmartPhones","3.1");

        // DEPARTAMENTO LAPTOPS
        Productos departamentoLaptops = new ProductosDepartamento(laptops, "Laptops", "3.2");

        // DEPARTAMENTO TELEVISIONES
        Productos departamentoTelevisiones = new ProductosDepartamento(televisiones, "Televisiones", "3.3");




        // CATÁLOGO
        Productos catalogo = new ProductosDepartamento(allDeparments, "Catálogo","0");


        // AGREGANDO PRODUCTOS A SUS RESPECTIVOS DEPARTAMENTOS



        // AGREGANDO FRUTAS
        departamentoFrutas.add(manzana);

        // AGREGANDO CARNES
        departamentoCarnes.add(carneRes);

        // AGREGANDO LACTEOS
        departamentoLacteos.add(lecheSantaClara);

        // AGREGANDO DEPARTAMENTOS ALIMENTOS
        departamentoAlimentos.add(departamentoFrutas);
        departamentoAlimentos.add(departamentoCarnes);
        departamentoAlimentos.add(departamentoLacteos);


        // AGREGANDO SMARTPHONES
        departamentoSmartPhones.add(iPhone);

        // AGREGANDO LAPTOPS
        departamentoLaptops.add(hPVictus);

        // AGREGANDO TELEVISIONES
        departamentoTelevisiones.add(hisenseTV);

        // AGREGANDO DEPARTAMENTOS ELECTRONICA
        departamentoElectronica.add(departamentoSmartPhones);
        departamentoElectronica.add(departamentoLacteos);
        departamentoElectronica.add(departamentoTelevisiones);





        // AGREGANDO LAVADORAS
        departamentoLavadoras.add(lavadoraWhirlpool);

        // AGREGANDO MICROONDAS
        departamentoMicroondas.add(microondasSamsung);

        // AGREGANDO REFRIGERADORES
        departamentoRefrigeradores.add(refrigeradorMabe);

        // AGREGANDO DEPARTAMENTOS ELECTRODOMESTICOS.
        departamentoElectrodomesticos.add(departamentoLavadoras);
        departamentoElectrodomesticos.add(departamentoMicroondas);
        departamentoElectrodomesticos.add(departamentoRefrigeradores);




        // AGREGANDO LOS DEPARTAMENTOS A SU CATÁLOGO

        // DEPARTAMENTO ALIMENTOS.
        catalogo.add(departamentoAlimentos);

        // DEPARTAMENTO ELECTRONICA
        catalogo.add(departamentoElectronica);

        // DEPARTAMENTO ELECTRODOMESTICOS
        catalogo.add(departamentoElectrodomesticos);


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
   
}
 