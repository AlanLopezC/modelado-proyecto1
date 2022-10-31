package Catalogo;
import java.io.Serializable;
import java.util.ArrayList;

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

    @Override
    public void hacerDescuento(String barcode, double descountIn) {
        CatalogoReal.hacerDescuento(barcode, descountIn);
    }

    public void actualizarCatalogo(String barCode, double descountIn){
        CatalogoReal.hacerDescuento(barCode, descountIn);
    }

    public Productos getProducto(String br){
        return CatalogoReal.getProducto(br);
    }

}