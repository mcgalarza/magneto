package com.meli.magneto.validations

import com.meli.magneto.exception.InvalidParametersException
import com.meli.magneto.model.DNARequest
import spock.lang.Specification

class LengthValidationRuleTest extends Specification {

    def lengthValidationRule = new LengthValidationRule()

    def "Validate invalid length"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["AA", "BBB"]
        }

        when:
        lengthValidationRule.validate(dnaRequest)

        then:
        thrown InvalidParametersException
    }
}