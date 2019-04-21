package com.meli.magneto;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN, reason = "Non mutant")
public class NonMutantException extends RuntimeException{}