package org.example.exception;

public class InvalidCredentialException
        extends BusinessException {

    public InvalidCredentialException(String message) {

        super(message);
    }
}