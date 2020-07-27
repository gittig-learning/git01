package org.example.mvc.exception;

public class LoginErrorException extends RuntimeException{
    public LoginErrorException() {
        super();
    }

    public LoginErrorException(String message) {
        super(message);
    }
}
