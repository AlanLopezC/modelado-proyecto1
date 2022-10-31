package Catalogo;

import Productos.*;
import java.util.*;
/*
 Interface creada para poder usar proxys para el catálogo del servidor.
*/

public interface CatalogoServer{
   /**
    * Método que nos muestra el catálogo del servidor de la tienda. 
    **/
   public abstract void mostrarCatalogo();

   public void hacerDescuento(String barcode, double descount);
}
