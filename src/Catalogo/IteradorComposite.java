package Catalogo;
import java.util.*;
public class IteradorComposite implements Iterator{
    Stack stack = new Stack();

    public IteradorComposite(Iterator iterador){
        stack.push(iterador);
    }

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