package Catalogo;
import java.util.*;
public abstract class Productos{

    public  void printDatos(){

    }

    public  void add(Productos producto){

    }

    public  void remove(Productos producto){

    }
    public String getNombre(){
        return "";
    }

    public String getBarcode(){
        return "";
    }

    public ArrayList<Productos> getProductos(){
        return new ArrayList<Productos>();
    }

    public Iterator crearIterador(){
        return new NullIterador();
    }

}