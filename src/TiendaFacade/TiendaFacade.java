package TiendaFacade;

import Tienda.DoesNotPassValidation;
import Tienda.Tienda;
import Tienda.LatinSpanishStore;
import Tienda.EnglishStore;
import Tienda.SpanishStore;
import Users.Users;
import Catalogo.CatalogoProxy;
import Remote.Remote;
import Users.User;


import java.io.IOException;
import java.net.Socket;
import java.util.*;


public class TiendaFacade {

    private CatalogoProxy catalogoProxy;

    private Map<String, User> baseDatosUsuarios = new HashMap<>();

    private User user;

    private Tienda tienda;

    public static void clearConsole(){
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e){
            throw new RuntimeException(e);
        }
    }

    public static void load(String loadString){
        for (int i = 0; i < loadString.length(); i++) {
            System.out.print("\n" + loadString.charAt(i) + "\n");
            try {
                Thread.sleep(2);
            }
            catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    public static void sleepFor(String sleepIn){
        try {
            System.out.print(sleepIn);
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            System.out.print(e);
        }
    }

    public void presentation(){
        String[] palabras = {Design.INICIOSPANISH, Design.INICIOENGLISH, Design.INICIOFRANCES};
        for (int i = 0; i < palabras.length; i++){
            String palabra = palabras[i];
            for (int j = 0; j < palabra.length(); j++){
                try {
                    System.out.print(palabra.charAt(j));
                    Thread.sleep(2);
                }
                catch (InterruptedException e){
                    throw new RuntimeException();
                }
            }
            clearConsole();
        }
    }

    public void extraerUsuarios(){

        // OBJETOS

        Users users = new Users();
        for (User user: users.getUsers()){
            baseDatosUsuarios.put(user.getUsername(), user);
        }
    }


    public void extraerCatalogo(){

        try {

            Socket socket = new Socket("localhost", 8080);
            Remote remote = new Remote(socket);
            catalogoProxy = (CatalogoProxy) remote.receive();
            load(Design.OBTENIENDOCATALOGO);
            remote.close();

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void logIn(){

        // OBJETOS
        Scanner scannerString = new Scanner(System.in);

        // VARIABLES
        String userName; // USERS NAME
        String passWord; // PASSWORD

        while (true){
            try {
                System.out.print(Design.LOGIN);
                System.out.print("User: ");
                userName = scannerString.nextLine().strip();
                System.out.print("Password: ");
                passWord = scannerString.nextLine().strip();

                if (!baseDatosUsuarios.containsKey(userName)){
                    throw new DoesNotPassValidation();
                }

                user = baseDatosUsuarios.get(userName);

                if (!user.getPassword().equals(passWord)){
                    throw new DoesNotPassValidation();
                }

                clearConsole();
                load("Obteniendo usuario ..");
                clearConsole();
                break;
            }
            catch (DoesNotPassValidation e){
                clearConsole();
                try {
                    System.out.print(Design.ERRORLOGIN);
                    Thread.sleep(5000);
                    clearConsole();
                }
                catch (InterruptedException a) {
                    System.out.print(a);
                }
            }
        }
    }

    public void obtenerTienda(){
        switch (user.getCountry()) {
            case "MX":  tienda = new LatinSpanishStore();
            break;
            case "US": tienda = new EnglishStore();
            break;
            case "ES": tienda = new SpanishStore();
        }
    }

    public boolean menuPrincipal(){

        // OBJETOS
        Scanner scanner = new Scanner(System.in);

        // VARIABLES
        String input;
        int inputIn;
        boolean exit = false;

        while (!exit){
            while (true){

                try {
                    System.out.print(tienda.mostrarMenu());
                    input = scanner.nextLine().strip();
                    inputIn = Integer.parseInt(input);
                    if (!(1 <= inputIn & inputIn <= 4)){
                        throw new NumberFormatException();
                    }
                    break;
                }
                catch (NumberFormatException e){
                    System.out.print(Design.OPCIONINVALIDA);
                    load(Design.OPCIONINVALIDA);
                    clearConsole();
                }
            }

            switch (inputIn) {
                case 1: tienda.mostrarCatalogo();
                break;
                case 2: tienda.realizarCompra(catalogoProxy, user);
                break;
                case 3: {
                    tienda.salirCerrarSesion(user);
                    exit = true;
                    break;
                }
                case 4:{
                    return true;
                }
            }
        }
        return false;
    }


    public void startStore(){

        boolean salirSistema = false;

        extraerUsuarios(); // OBTENER USUARIOS
        // extraerCatalogo(); // OBTENER CATÁLOGO.
        presentation(); // PRESENTACIÓN.


        while (!salirSistema){

            logIn(); // INICIAR SESIÓN.
            obtenerTienda(); // OBTENER LA CONFIGURACIÓN DE LA TIENDA.
            System.out.print(tienda.saludar()); // SALUDAR.
            salirSistema = menuPrincipal(); // ESTAR EN EL MENÚ PRINCIPAL.
        }

        // ENVIAR OFERTAS.

    }

    public void closeStore(){

        // CERRAR SESIÓN.
        tienda.despedirse(); // DESPEDIRSE.
    }

}
