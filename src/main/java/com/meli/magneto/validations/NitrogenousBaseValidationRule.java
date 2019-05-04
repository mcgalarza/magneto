package com.meli.magneto.validations;

import com.meli.magneto.exception.InvalidParametersException;
import com.meli.magneto.model.DNARequest;

import java.util.Arrays;
import java.util.List;

public class NitrogenousBaseValidationRule implements DNAValidationRule {
    private static String NITROGENOUS_BASE_PATTERN = "[A|T|C|G]+";

    @Override
    public void validate(DNARequest dnaRequest) {
        Integer dnaLength = dnaRequest.getDna().length;
        List<String> dnaSequence = Arrays.asList(dnaRequest.getDna());

        dnaSequence.forEach(s -> {
            if (!s.matches(NITROGENOUS_BASE_PATTERN))
                throw new InvalidParametersException("DNA must be formed by its nitrogenous base just with A, T, C, G characters");
        });

    }
}