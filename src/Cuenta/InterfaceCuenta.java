package Cuenta;
/**
* Clase interface de una cuenta bancaria.
 */
public interface InterfaceCuenta{

    /**
    * Método que simula el retiro de dinero de la cuenta.
    * @param monto monto a retirar.
    * @return double monto retirado. 
     */
    public double retirar( double monto);
    
    /** 
    Método que simula algún depósito a la cuenta.
    @param monto dinero que se depositará a la cuenta.
    */
    public void depositar(double monto);
}