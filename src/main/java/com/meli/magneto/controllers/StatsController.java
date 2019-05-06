package com.meli.magneto.controllers;

import com.meli.magneto.model.Stats;
import com.meli.magneto.services.StatsService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/stats")
public class StatsController {

    private Logger logger = LogManager.getLogger(StatsController.class);
    private StatsService statsService;

    @Autowired
    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Stats> getStats() {
        logger.info("Received a /stats request");
        return new ResponseEntity<>(statsService.getStats(), HttpStatus.OK);
    }
}
