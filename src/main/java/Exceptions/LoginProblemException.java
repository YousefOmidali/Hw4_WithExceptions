package Exceptions;

public class LoginProblemException extends RuntimeException{
    public LoginProblemException() {
    }

    public LoginProblemException(String message) {
        super(message);
    }
}
