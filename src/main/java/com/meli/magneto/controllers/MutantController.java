package com.meli.magneto.controllers;

import com.meli.magneto.exception.InvalidParametersException;
import com.meli.magneto.model.DNARequest;
import com.meli.magneto.services.MutantService;
import com.meli.magneto.validations.DNAValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/mutant")
public class MutantController {

    private MutantService mutantService;
    private DNAValidator dnaValidator;

    @Autowired
    public MutantController(MutantService mutantService, DNAValidator dnaValidator) {
        this.mutantService = mutantService;
        this.dnaValidator = dnaValidator;
    }

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest) throws Exception{
        try {
            dnaValidator.validate(dnaRequest);
        } catch (InvalidParametersException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        Boolean mutant = mutantService.isMutant(dnaRequest);
        HttpStatus status = mutant ? HttpStatus.OK : HttpStatus.FORBIDDEN;
        return new ResponseEntity<>("", status);
    }

}
