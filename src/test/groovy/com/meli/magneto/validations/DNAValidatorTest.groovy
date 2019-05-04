package com.meli.magneto.validations

import com.meli.magneto.exception.InvalidParametersException
import com.meli.magneto.model.DNARequest
import spock.lang.Specification

class DNAValidatorTest extends Specification {
    def dnaValidator = new DNAValidator()

    def "Validate invalid length"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["AA", "BBB"]
        }

        when:
        dnaValidator.validate(dnaRequest)

        then:
        thrown InvalidParametersException
    }
}
