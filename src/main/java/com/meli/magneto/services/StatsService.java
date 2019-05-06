package com.meli.magneto.services;

import com.meli.magneto.model.Stats;
import com.meli.magneto.repositories.DNARepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private Logger logger = LogManager.getLogger(StatsService.class);
    private DNARepository dnaRepository;

    @Autowired
    public StatsService(DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    @Cacheable(value = "stats", key = "'_stat'")
    public Stats getStats() {
        Long mutantCount = dnaRepository.countByMutant(true);
        Long humanCount = dnaRepository.countByMutant(false);
        Stats stats = new Stats(mutantCount, humanCount);
        logger.info("count_mutant_dna: " + mutantCount + " - count_human_dna: " + humanCount + " - ratio: " + stats.getRatio());
        return stats;
    }
}