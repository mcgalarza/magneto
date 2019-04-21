package com.meli.magneto.services;

import com.meli.magneto.NonMutantException;
import com.meli.magneto.model.DNA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MutantService {
    @Autowired
    DNAAnalyzerService dnaAnalyzerService;

    public void analyzeDNA(DNA dna) throws NonMutantException{
        Boolean isMutant = dnaAnalyzerService.isMutant(dna.getDnaSequence());
        if (!isMutant)
            throw new NonMutantException();
    }
}
