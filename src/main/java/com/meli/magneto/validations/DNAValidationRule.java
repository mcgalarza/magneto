package com.meli.magneto.validations;

import com.meli.magneto.model.DNARequest;

public interface DNAValidationRule {

    void validate(DNARequest dnaRequest);

}
