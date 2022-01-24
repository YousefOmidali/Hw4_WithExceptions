package Exceptions;

public class FailedToDeleteTheTicketException extends RuntimeException {
    public FailedToDeleteTheTicketException() {
    }

    public FailedToDeleteTheTicketException(String message) {
        super(message);
    }
}
