package Catalogo;
import Pruductos.*;
import java.util.*;
import java.io.Serializable;

public class Catalogo implements Serializable, CatalogoServer{
    private ArrayList<Producto> departamento1 = new ArrayList<Producto>();
    private ArrayList<Producto> departamento2 = new ArrayList<Producto>();
    private ArrayList<ArrayList> departamentos = new ArrayList<ArrayList>();

    public Catalogo(){
        Smartphone iPhone = new Smartphone("Iphone 3", "12345", "Celulares", 7999.00,
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

        Smartphone samsung = new Smartphone("Samsung", "4353", "Electronica", 1000.00,
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

        Fruta manzana = new Fruta("Manzana", "4425566", "Alimentos", 10, "- Traído de las granjas.");
        manzana.agregarPropiedad("Color", "Rojo");
        manzana.agregarPropiedad("Tiene gusanos", false);

        departamento1.add(manzana);
        departamento2.add(iPhone);
        departamento2.add(samsung);
        departamentos.add(departamento1);
        departamentos.add(departamento2);
        

    }
    public void mostrarCatalogo(){ 
        for(int i = 0; i< departamentos.size();i++){
            Producto productoD = (Producto) departamentos.get(i).get(0);
            
            System.out.println("***** Departamento de "+ productoD.getDepartamento() +  "******");
                for(int j = 0 ; j < departamentos.get(i).size();j++){
                    System.out.println(j+".-"+ departamentos.get(i).get(j));
                }
          
        } 
    }
   /* public void mostrarCatalogo(){
        System.out.println("Hay un problema");
    }*/

   
}
 