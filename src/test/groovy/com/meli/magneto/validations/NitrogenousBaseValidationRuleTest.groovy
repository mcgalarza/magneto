package com.meli.magneto.validations

import com.meli.magneto.exception.InvalidParametersException
import com.meli.magneto.model.DNARequest
import spock.lang.Specification

class NitrogenousBaseValidationRuleTest extends Specification {

    def nitrogenousBaseValidationRule = new NitrogenousBaseValidationRule()

    def "Validate non nitrogenous base"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["AAA", "BBB", "CCC"]
        }

        when:
        nitrogenousBaseValidationRule.validate(dnaRequest)

        then:
        thrown InvalidParametersException
    }

    def "Validate nitrogenous base"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["AAA", "AAA", "CCC"]
        }

        when:
        def result = nitrogenousBaseValidationRule.validate(dnaRequest)

        then:
        assert result == null
    }
}