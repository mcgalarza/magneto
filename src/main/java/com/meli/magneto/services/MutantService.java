package com.meli.magneto.services;

import com.meli.magneto.exception.NonMutantException;
import com.meli.magneto.model.DNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {
    private DNAAnalyzerService dnaAnalyzerService;

    @Autowired
    MutantService(DNAAnalyzerService dnaAnalyzerService) {
        this.dnaAnalyzerService = dnaAnalyzerService;
    }

    public void analyzeDNA(DNA dna) throws NonMutantException {
        dnaAnalyzerService.checkDNASequence(dna.getDna());
        Boolean isMutant = dnaAnalyzerService.isMutant(dna.getDna());
        if (!isMutant)
            throw new NonMutantException();
    }
}
