package com.meli.magneto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class InvalidParametersException extends RuntimeException{

    public InvalidParametersException(String message) {
        super(message);
    }

}
