package Catalogo;
import java.util.Iterator;

public class NullIterador implements Iterator{
    public Object next(){
        return null;
    }

    public boolean hasNext(){
        return false;
    }

    public void remove(){
        
    }
}