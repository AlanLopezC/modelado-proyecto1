package Catalogo;
import java.io.Serializable;
/*
Clase que simula un proxy para el catálogo.
*/
public class CatalogoProxy implements Serializable, CatalogoServer{
    private Catalogo CatalogoReal;
    /**
    * COnstructor de la clase
    **/
    public CatalogoProxy(Catalogo CatalogoReal){
        this.CatalogoReal = CatalogoReal;
    }
    /**
    * Método que nos muestra el catálogo del servidor de la tienda. 
    **/
    public void mostrarCatalogo(){
        CatalogoReal.mostrarCatalogo();
    }

    public Productos getProducto(String br){
        return CatalogoReal.getProducto(br);
    }

}