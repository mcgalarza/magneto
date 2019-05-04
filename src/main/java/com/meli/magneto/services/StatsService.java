package com.meli.magneto.services;

import com.meli.magneto.model.Stats;
import com.meli.magneto.repository.DNARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatsService {

    private DNARepository dnaRepository;

    @Autowired
    public StatsService(DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public Stats getStats() {
        Long mutantCount = dnaRepository.countByMutant(true);
        Long human = dnaRepository.countByMutant(false);
        Stats stats = new Stats(mutantCount, human);
        return stats;
    }
}
