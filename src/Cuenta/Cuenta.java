package Cuenta;
import java.io.Serializable;
/** 
* Clase que simula una cuenta bancaria.
**/
public class Cuenta implements Serializable, InterfaceCuenta{
    /**
    * código de seguridad de la cuenta.
    **/
    private int cvv;

    /**
    * Saldo con el que se inicia la cuenta..
    **/
    private double saldoInicial;
    /**
    * Nombre del propietario de la cuenta.
    **/
    private String usuario;

    private double saldoActual;


    /** 
    *Método constructor de la clase
    **/

    public Cuenta(int cvv,String usuario, double saldo){
        this.cvv = cvv;
        saldoInicial = saldo;
        saldoActual = saldo;
        this.usuario = usuario;
    }
    /**
    * Método que simula el retiro de dinero de la cuenta.
    * @param monto monto a retirar.
    * @return double monto retirado. 
     */
    public double retirar( double monto){
        if(saldoActual>= monto){
            saldoActual =-monto;
            return monto;
        }else {
            System.out.println("No tienes el suficiente dinero :(");
            return 0;
        }
    }
    
    /** 
    Método que simula algún pago con la targeta.
    @param monto dinero a pagar.
    */
    public void pagar(double monto){
        saldoActual -= retirar(monto);
    }

    /** 
    Método que simula algún depósito a la cuenta.
    @param monto dinero que se depositará a la cuenta.
    */
    public void depositar(double monto){
        saldoActual += monto;
    }

    /**
    * Método que devuelve el código de seguridad de la cuenta.
    * @return int código de seguridad.
     */
    public int getIdCuenta(){
        return cvv;
    }

    /**
    * Método que devuelve el nombre del usuario de la cuenta.
    * @return String nombre del usuario.
     */
    public String getusuario(){
        return usuario;
    }

    /**
    * Método que devuelve el saldo de la cuenta.
    * @return double ssaldo actual de la cuenta.
     */
    public double getSaldo(){
        return saldoActual;
    }


}