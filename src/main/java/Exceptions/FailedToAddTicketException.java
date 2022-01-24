package Exceptions;

public class FailedToAddTicketException extends RuntimeException {
    public FailedToAddTicketException() {
    }

    public FailedToAddTicketException(String message) {
        super(message);
    }
}
