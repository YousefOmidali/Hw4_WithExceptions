package Exceptions;

public class FailedToRegisterTheCinemaException extends RuntimeException {
    public FailedToRegisterTheCinemaException() {
    }

    public FailedToRegisterTheCinemaException(String message) {
        super(message);
    }
}
