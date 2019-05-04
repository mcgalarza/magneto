package com.meli.magneto.validations;

import com.meli.magneto.model.DNARequest;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DNAValidator {
    List<DNAValidationRule> validationRules = new ArrayList<>();

    public DNAValidator() {
        validationRules.add(new LengthValidationRule());
        validationRules.add(new NitrogenousBaseValidationRule());
        validationRules.add(new NonEmptyValidationRule());
    }

    public void validate(DNARequest dnaRequest) {
        validationRules.forEach(dnaValidationRule -> dnaValidationRule.validate(dnaRequest));
    }
}
