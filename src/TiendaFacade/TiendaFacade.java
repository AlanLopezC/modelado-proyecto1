package TiendaFacade;

import Tienda.DoesNotPassValidation;
import Tienda.Tienda;
import Tienda.LatinSpanishStore;
import Tienda.EnglishStore;
import Tienda.SpanishStore;
import Users.Users;
import Users.User;


import java.io.IOException;
import java.util.*;


public class TiendaFacade {

    private Map<String, User> baseDatosUsuarios = new HashMap<>(); // BASE DE DATOS DE USUARIOS.

    private User user; // USUARIO EN LA TIENDA.

    private Tienda tienda; // TIENDA.

    /**
     * Método para limpiar la consola.
     */
    public static void clearConsole(){
        try {
            new ProcessBuilder("clear").inheritIO().start().waitFor();
        } catch (InterruptedException | IOException e){
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para "Hacer una pantalla de carga."
     * @param loadString - Mensaje que estará cargando.
     */
    public static void load(String loadString){
        for (int i = 0; i < loadString.length(); i++) {
            // System.out.print("\n" + loadString.charAt(i) + "\n");
            System.out.print(loadString.charAt(i));
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                throw new RuntimeException();
            }
        }
    }

    /**
     * Mëtodo para que se muestre solo por unos segundos un mensaje.
     * @param sleepIn - Mensaje.
     */
    public static void sleepFor(String sleepIn){
        try {
            System.out.print(sleepIn);
            Thread.sleep(5000);
        }
        catch (InterruptedException e){
            System.out.print(e);
        }
    }

    /**
     * Método para que se muestre solo por unos segundos un mensaje.
     * @param sleepIn - Mensaje.
     * @param time - Cantidad en milisegundos.
     */
    public static void sleepFor(String sleepIn, int time){
        try {
            System.out.print(sleepIn);
            Thread.sleep(time);
        }
        catch (InterruptedException e){
            System.out.print(e);
        }
    }

    /**
     * Presentación del Programa.
     */
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

    /**
     * Método para extraer usuarios.
     */
    public void extraerUsuarios(){

        // OBJETOS

        Users users = new Users();
        for (User user: users.getUsers()){
            baseDatosUsuarios.put(user.getUsername(), user);
        }
    }

    /**
     * Método para entrar a una cuenta de la Tienda.
     */
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

    /**
     * Método para obtener la respectiva Tienda dependiendo del país del usuario.
     */
    public void obtenerTienda(){
        switch (user.getCountry()) {
            case "MX":  tienda = new LatinSpanishStore();
            break;
            case "US": tienda = new EnglishStore();
            break;
            case "ES": tienda = new SpanishStore();
        }
    }

    /**
     * Método que te maneja y muestra el menú principal de la Tienda.
     * @return Boolean - True si quieres salir del menú principal, False cualquier otro caso.
     */
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
                    clearConsole();
                    sleepFor(Design.OPCIONINVALIDA);
                    clearConsole();
                }
            }

            clearConsole();

            switch (inputIn) {
                case 1: tienda.mostrarCatalogo();
                break;
                case 2: tienda.realizarCompra(user);
                break;
                case 3: {
                    tienda.salirCerrarSesion(user);
                    exit = true;
                    break;
                }
                case 4:{
                    System.out.print(tienda.despedirse());
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * Método que inicia el programa.
     */
    public void iniciarPrograma(){

        Random random = new Random();
        boolean abraDescuento = random.nextBoolean();

        boolean salirSistema = false;

        extraerUsuarios(); // OBTENER USUARIOS
        presentation(); // PRESENTACIÓN.


        while (!salirSistema){

            logIn(); // INICIAR SESIÓN.
            obtenerTienda(); // OBTENER LA CONFIGURACIÓN DE LA TIENDA.
            if (abraDescuento){
                tienda.mostrarOferta(); // MOSTRAR OFERTA.
            }
            System.out.print(tienda.saludar()); // SALUDAR.
            salirSistema = menuPrincipal(); // ESTAR EN EL MENÚ PRINCIPAL.
        }
    }

}
