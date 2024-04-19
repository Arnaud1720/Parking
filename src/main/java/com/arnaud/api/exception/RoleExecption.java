package com.arnaud.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RoleExecption extends RuntimeException {

    public RoleExecption(String message) {
        super(message);
    }

    public void getCause(String message) {
    }
}
