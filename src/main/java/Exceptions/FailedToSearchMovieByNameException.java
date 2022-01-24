package Exceptions;

public class FailedToSearchMovieByNameException extends RuntimeException{
    public FailedToSearchMovieByNameException() {
    }

    public FailedToSearchMovieByNameException(String message) {
        super(message);
    }
}
