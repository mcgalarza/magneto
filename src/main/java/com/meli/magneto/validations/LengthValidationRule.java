package com.meli.magneto.validations;

import com.meli.magneto.exception.InvalidParametersException;
import com.meli.magneto.model.DNARequest;

import java.util.Arrays;
import java.util.List;

public class LengthValidationRule implements DNAValidationRule {
    @Override
    public void validate(DNARequest dnaRequest) {
        Integer dnaLength = dnaRequest.getDna().length;
        List<String> dnaSequence = Arrays.asList(dnaRequest.getDna());

        dnaSequence.forEach(s -> {
            if (s.length() != dnaLength)
                throw new InvalidParametersException("Each string in sequence must have the same length");
        });
    }
}