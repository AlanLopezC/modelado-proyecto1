package Catalogo;
import java.util.Iterator;
/**
* Clase que simula un iterador nulo.
*/
public class NullIterador implements Iterator{
    /**
    *Método que regresa un objeto nulo debido a que no hay objetos para iterar.
    *@return Objet objeto nulo.
     */
    public Object next(){
        return null;
    }
    /** 
    * Método que siempre regresa false debido a que no ha un siguiente elemento para iterar.
    * @return boolean false. 
    */
    public boolean hasNext(){
        return false;
    }

    public void remove(){
        
    }
}