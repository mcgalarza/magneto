package com.meli.magneto.services;

import com.meli.magneto.model.Stats;
import com.meli.magneto.repositories.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class StatsService {
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
        return stats;
    }
}
