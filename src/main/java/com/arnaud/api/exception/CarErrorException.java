package com.arnaud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CarErrorException extends RuntimeException {

    public CarErrorException(String message) {
        super(message);
    }
}
