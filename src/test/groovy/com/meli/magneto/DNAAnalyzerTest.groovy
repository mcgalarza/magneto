package com.meli.magneto

import spock.lang.Specification

class DNAAnalyzerTest extends Specification {
    def "IsMutant"() {
        given:
        int right = 2
        int left = 2

        when:
        int result = right + left

        then:
        result == 4
    }
}
