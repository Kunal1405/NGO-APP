package com.Kunal.Login.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserNotExists extends RuntimeException {
    public UserNotExists(String message) {
        super(message);
    }
}
