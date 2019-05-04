package com.meli.magneto.controllers;

import com.meli.magneto.model.DNARequest;
import com.meli.magneto.services.MutantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/mutant")
public class MutantController {

    @Autowired
    private MutantService mutantService;

    @PostMapping
    public ResponseEntity<String> isMutant(@RequestBody DNARequest dnaRequest) throws Exception{
        //TODO: call validation rules and return bad request
        mutantService.isMutant(dnaRequest);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
