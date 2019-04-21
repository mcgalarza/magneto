package com.meli.magneto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Invalid parameters")
public class InvalidParametersException extends RuntimeException{
}
