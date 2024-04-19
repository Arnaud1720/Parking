package com.arnaud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class NotPermitedException extends RuntimeException {

    public NotPermitedException(String message) {
        super(message);
    }
}
