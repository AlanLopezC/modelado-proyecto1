package Tienda;

public class DoesNotPassValidation extends Exception {

    public DoesNotPassValidation(String message){
        super(message);
    }

    public DoesNotPassValidation(){
        super("Error");
    }
}
