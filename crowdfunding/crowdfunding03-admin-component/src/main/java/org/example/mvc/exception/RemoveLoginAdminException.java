package org.example.mvc.exception;

public class RemoveLoginAdminException extends RuntimeException {
    public RemoveLoginAdminException() {
    }

    public RemoveLoginAdminException(String message) {
        super(message);
    }
}
