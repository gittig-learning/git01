package org.example.mvc.exception;

public class AddAdminAcctException extends RuntimeException {
    public AddAdminAcctException() {
    }

    public AddAdminAcctException(String message) {
        super(message);
    }
}
