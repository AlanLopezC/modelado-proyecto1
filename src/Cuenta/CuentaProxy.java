package Cuenta;
import java.io.Serializable;

public class CuentaProxy implements Serializable, InterfaceCuenta{
    private Cuenta cuentaReal;
    private double saldo;
    private double deposito;
    private double retiro;

    public CuentaProxy(Cuenta cuenta){
        cuentaReal = cuenta;
        saldo = cuenta.getSaldo();
        deposito = 0;
        retiro = 0;

    }
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

    public void depositar( double monto){
        deposito = monto;
    }

    public void actualizarDeposito(){
        cuentaReal.depositar(deposito);
        
    
    } 

    public void actualizarRetiro(){
        cuentaReal.pagar(retiro);
        
    }



}