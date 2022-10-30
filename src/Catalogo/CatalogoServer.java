package Catalogo;
import Pruductos.*;
import java.util.*;
/*
 Interface creada para poder usar proxys para el catálogo del servidor.
*/
public interface CatalogoServer{
   /**
    * Método que nos muestra el catálogo del servidor de la tienda. 
    **/
   public abstract void mostrarCatalogo();
}
