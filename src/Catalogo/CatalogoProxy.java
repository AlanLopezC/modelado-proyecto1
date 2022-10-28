package Catalogo;
import java.io.Serializable;
public class CatalogoProxy implements Serializable, CatalogoServer{
    private Catalogo CatalogoReal;

    public CatalogoProxy(Catalogo CatalogoReal){
        this.CatalogoReal = CatalogoReal;
    }

    public void mostrarCatalogo(){
        CatalogoReal.mostrarCatalogo();
    }


}