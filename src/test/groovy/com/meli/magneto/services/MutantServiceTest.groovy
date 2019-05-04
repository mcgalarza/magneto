package com.meli.magneto.services

import com.meli.magneto.model.DNA
import com.meli.magneto.model.DNARequest
import com.meli.magneto.repository.DNARepository
import spock.lang.Specification

class MutantServiceTest extends Specification {
    def dnaRepository = Mock(DNARepository) {
        save(_ as DNA) >> new DNA()
    }

    def mutantService = new MutantService(dnaRepository)

    def "isMutant test with mutant dna sequence"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ATGCGA", "CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]
        }

        when:
        def mutant = mutantService.isMutant(dnaRequest)

        then:
        assert mutant == true
    }

    def "isMutant test with non mutant dna sequence"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ATGCGA", "CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]
        }

        when:
        def mutant = mutantService.isMutant(dnaRequest)

        then:
        assert mutant == false
    }

    def "Test isMutant with just 1 sequence with 4 equal characters"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ATGCAA", "CAGTGC","TTATGT","AGAAGG","TCCCTA","TCACTG"]
        }

        when:
        def mutant = mutantService.isMutant(dnaRequest)

        then:
        assert mutant == false
    }

    def "Test isMutant with all characters A"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["AAAAAA", "AAAAAA","AAAAAA","AAAAAA","AAAAAA","AAAAAA"]
        }

        when:
        def mutant = mutantService.isMutant(dnaRequest)

        then:
        assert mutant == true
    }

    def "Test isMutant with matching pattern in the end of the sequence"() {
        given:
        def dnaRequest = Mock(DNARequest) {
            getDna() >> ["ABCDE", "ABJED", "PHIED", "FGHED", "ABCED"]
        }

        when:
        def mutant = mutantService.isMutant(dnaRequest)

        then:
        assert mutant == true
    }


}
