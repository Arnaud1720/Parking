package com.arnaud.api.exception;

public class UserNotFondException extends RuntimeException {
    public UserNotFondException(String message) {
        super(message);
    }
}
