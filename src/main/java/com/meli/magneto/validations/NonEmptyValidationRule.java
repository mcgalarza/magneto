package com.meli.magneto.validations;

import com.meli.magneto.exception.InvalidParametersException;
import com.meli.magneto.model.DNARequest;

public class NonEmptyValidationRule implements DNAValidationRule {
    @Override
    public void validate(DNARequest dnaRequest) {
        if (dnaRequest.getDna().length < 1)
            throw new InvalidParametersException("DNA sequence cannot be empty");
    }
}
