package Cuenta;
import java.io.Serializable;

/**
*Clase que simula un proxy para las cuentas bancarias.
 */
public class CuentaProxy implements Serializable, InterfaceCuenta{
    /**
    * cuenta en la que se utilizará el proxy.
     */
    private Cuenta cuentaReal;
    /**
    * saldo de la cuenta que utilizzará el proxy.
    */
    private double saldo;

    /**
    * deposito hecho con la cuenta proxy.
     */
    private double deposito;
    /**
    * retiro hecho con la cuenta proxy.
     */
    private double retiro;
    /**
    * Método constructor de la clase.
    * @param cuenta Cuenta que utilizará el proxy.
     */
    public CuentaProxy(Cuenta cuenta){
        cuentaReal = cuenta;
        saldo = cuenta.getSaldo();
        deposito = 0;
        retiro = 0;

    }
    /**
    * Método que simula el retiro de dinero de la cuenta proxy.
    * @param monto monto a retirar.
    * @return double monto retirado. 
     */
    public double retirar(double monto){
        if(saldo>= monto){
            saldo =-monto;
            retiro = monto;
            
            return monto;
        }else {
            System.out.println("No tienes el suficiente dinero :(");
            return 0;
        }
    }
    /** 
    Método que simula algún depósito a la cuenta.
    @param monto dinero que se depositará a la cuenta.
    */
    public void depositar( double monto){
        deposito = monto;
    }
    /**
    *Método que actualiza el depósito a la cuenta real.
    */
    public void actualizarDeposito(){
        cuentaReal.depositar(deposito);
        
    
    } 
    /**
    *Método que actualiza el retiro a la cuenta real.
     */
    public void actualizarRetiro(){
        cuentaReal.pagar(retiro);
        
    }



}