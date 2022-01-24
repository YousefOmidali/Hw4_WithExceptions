package Exceptions;

public class NotInserException extends RuntimeException{
    public NotInserException() {
    }

    public NotInserException(String message) {
        super(message);
    }
}
