package com.meli.magneto.services

import com.meli.magneto.exception.InvalidParametersException
import spock.lang.Specification

class DNAAnalyzerServiceTest extends Specification {
    DNAAnalyzerService dnaAnalyzer = new DNAAnalyzerService()

    def "Test isMutant with mutant dna"() {
        given:
        String[] dna = ["ATGCGA", "CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"]

        when:
        Boolean result = dnaAnalyzer.isMutant(dna);

        then:
        result == true
    }

    def "Test isMutant with non-mutant dna"() {
        given:
        String[] dna = ["ATGCGA", "CAGTGC","TTATTT","AGACGG","GCGTCA","TCACTG"]

        when:
        Boolean result = dnaAnalyzer.isMutant(dna);

        then:
        result == false
    }

    def "Test isMutant with just 1 sequence with 4 equal characters"() {
        given:
        String[] dna = ["ATGCAA", "CAGTGC","TTATGT","AGAAGG","TCCCTA","TCACTG"]

        when:
        Boolean result = dnaAnalyzer.isMutant(dna);

        then:
        result == false
    }

    def "Test isMutant with all characters A"() {
        given:
        String[] dna = ["AAAAAA", "AAAAAA","AAAAAA","AAAAAA","AAAAAA","AAAAAA"]

        when:
        Boolean result = dnaAnalyzer.isMutant(dna);

        then:
        result == true
    }

    def "Test isMutant with characters in the end"() {
        given:
        String[] dna = ["ABCDE", "ABJED", "PHIED", "FGHED", "ABCED"]

        when:
        Boolean result = dnaAnalyzer.isMutant(dna);

        then:
        result == true
    }

    def "Test CheckDNASequence"() {
        given:
        String[] dna = ["AAA"]

        when:
        dnaAnalyzer.checkDNASequence(dna)

        then:
        thrown InvalidParametersException
    }

    def "Test CheckDNASequence different sequence length"() {
        given:
        String[] dna = ["AA", "BBB"]

        when:
        dnaAnalyzer.checkDNASequence(dna)

        then:
        thrown InvalidParametersException
    }

    def "Test CheckDNASequence invalid nitrogenous base"() {
        given:
        String[] dna = ["AAA", "BBB", "CCC"]

        when:
        dnaAnalyzer.checkDNASequence(dna)

        then:
        thrown InvalidParametersException
    }
}
