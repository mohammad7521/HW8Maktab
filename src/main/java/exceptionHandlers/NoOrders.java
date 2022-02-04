package exceptionHandlers;

public class NoOrders extends RuntimeException{
    public NoOrders() {
    }

    public NoOrders(String message) {
        super(message);
    }
}
