package Cuenta;
import java.io.Serializable;

public class Cuenta implements Serializable, InterfaceCuenta{

    private int cvv;
    private double saldoInicial;
    private String usuario;
    private double saldoActual;
    public Cuenta(int cvv,String usuario, double saldo){
        this.cvv = cvv;
        saldoInicial = saldo;
        saldoActual = saldo;
        this.usuario = usuario;
    }

    public double retirar( double monto){
        if(saldoActual>= monto){
            saldoActual =-monto;
            return monto;
        }else {
            System.out.println("No tienes el suficiente dinero :(");
            return 0;
        }
    }

    public void pagar(double monto){
        saldoActual -= retirar(monto);
    }

    public void depositar(double monto){
        saldoActual += monto;
    }

    public int getIdCuenta(){
        return cvv;
    }

    public String getusuario(){
        return usuario;
    }

    public double getSaldo(){
        return saldoActual;
    }
}