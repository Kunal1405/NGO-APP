package com.Kunal.Login.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class WrongLoginCredentials extends  RuntimeException{
    public WrongLoginCredentials(String message) {
        super(message);
    }
}
