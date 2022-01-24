package Exceptions;

public class FaildeToReservedException extends RuntimeException{
    public FaildeToReservedException() {
    }

    public FaildeToReservedException(String message) {
        super(message);
    }
}
