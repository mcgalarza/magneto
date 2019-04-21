package com.meli.magneto.controllers;

import com.meli.magneto.model.DNA;
import com.meli.magneto.services.DNAAnalyzerService;
import com.meli.magneto.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MutantController {
    @Autowired
    private MutantService mutantService;

    @RequestMapping(value = "/mutant", method = RequestMethod.POST)
    public void isMutant(@RequestBody DNA dna) throws Exception{
        mutantService.analyzeDNA(dna);
    }
}
