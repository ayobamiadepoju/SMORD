package com.smord.auth_service.exception;

public class EmailAlreadyExistException extends Throwable {
    public EmailAlreadyExistException(String message) {
        super(message);
    }
}
