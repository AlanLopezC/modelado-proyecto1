package Catalogo;
import java.util.*;
public abstract class Productos{
    
    /** 
    * Método que imprime la información (los datos) del departamento.
    **/
    public  void printDatos(){

    }
    /**
    * Método que agrega un producto al departamento.
    * @param producto producto a agregar al departamento. 
     **/
    public  void add(Productos producto){

    }
    /** 
    * Método que elimina un producto del departamento.
    * @param producto producto a remover. 
    **/
    public  void remove(Productos producto){
    
    /** 
    * Método que devuelve el nombre del departamento.
    * @return  String 
    **/
    }
    public String getNombre(){
        return "";
    }
    
     /** 
    * Método que devuelve el código de barras del departamento.
    * @return  String 
    **/
    public String getBarcode(){
        return "";
    }
    
    /** 
    * Método que devuelve la lista de productos del departamento.
    * @return  ArrayList<Productos> 
    **/
    public ArrayList<Productos> getProductos(){
        return new ArrayList<Productos>();
    }
    
    /** 
    * Método que crea el iterador de la clase.
    * @return Iterator
    **/
    public Iterator crearIterador(){
        return new NullIterador();
    }

}