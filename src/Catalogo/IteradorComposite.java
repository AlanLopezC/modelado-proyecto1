package Catalogo;
import java.util.*;

/**
Clase iteradora para los elementos creados con el patrón composite.
 */
public class IteradorComposite implements Iterator{
    /**
    * pila que nos ayudará a guardar iteradores.
     */
    Stack stack = new Stack();
    
    /**
    *Método constructor de la clase.
    * @param iterador 
     */
    public IteradorComposite(Iterator iterador){
        stack.push(iterador);
    }
    /**
    * Método que devuelve el siguiente elemtno de la iteración.
    * @return Objet siguiente elemtno de la iteración.
     */
    public Object next(){
        if(hasNext()){
            Iterator it = (Iterator) stack.peek();
            Productos producto = (Productos) it.next();
            if(producto instanceof ProductosDepartamento){
                stack.push(producto.crearIterador());
            }
            return producto;
        }else{
            return null;
        }
        
    }
    /**
    * Método que regresa true si hay mas elementos por iterar, false en caso contrario.
    *@return boolean frue si la iteración tiene más elementos. 
     */
    public boolean hasNext(){
        if(stack.empty()){
            return false;
        } else{
            Iterator it = (Iterator) stack.peek();
            if(!it.hasNext()){
                stack.pop();
                return hasNext();
            }else{
                return true;
            }
        }
    }

    public void remove(){

    }
}