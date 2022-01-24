package Exceptions;

public class FailedToRegisterTheCustomerException extends RuntimeException{
    public FailedToRegisterTheCustomerException() {
    }

    public FailedToRegisterTheCustomerException(String message) {
        super(message);
    }
}
