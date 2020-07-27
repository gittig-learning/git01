package org.example.mvc.exception;

public class AccessForbiddenException extends RuntimeException {

    public AccessForbiddenException() {
        super();
    }
    public AccessForbiddenException(String s) {
        super(s);
    }
}
