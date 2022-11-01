package Catalogo;
import java.io.Serializable;

/*
Clase que simula un proxy para el catálogo.
*/
public class CatalogoProxy implements Serializable, CatalogoServer{
    private Catalogo CatalogoReal;

    /**
    * Constructor de la clase
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

    /**
     * Método que te devuelve un producto del CatalogoProxy.
     * @param br - Barcode del Producto.
     * @return Productos - Producto del Catálogo.
     */
    public Productos getProducto(String br){
        return CatalogoReal.getProducto(br);
    }

}