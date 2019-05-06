package com.meli.magneto.validations

import com.meli.magneto.exception.InvalidParametersException
import com.meli.magneto.model.DNARequest
import spock.lang.Specification

class NonEmptyValidationRuleTest extends Specification {
    def nonemptyValidationRule = new NonEmptyValidationRule();

    def "Validate empty sequence"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> []
        }

        when:
        nonemptyValidationRule.validate(dnaRequest)

        then:
        thrown InvalidParametersException
    }

    def "Validate non empty sequence"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ATGCGA", "CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
        }

        when:
        def result = nonemptyValidationRule.validate(dnaRequest)

        then:
        assert result == null
    }
}