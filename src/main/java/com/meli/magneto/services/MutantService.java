package com.meli.magneto.services;

import com.meli.magneto.model.DNA;
import com.meli.magneto.model.DNARequest;
import com.meli.magneto.repository.DNARepository;
import com.meli.magneto.strategies.DownSequenceFinder;
import com.meli.magneto.strategies.RightDownSequenceFinder;
import com.meli.magneto.strategies.RightSequenceFinder;
import com.meli.magneto.strategies.SequenceFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MutantService {
    private DNARepository dnaRepository;

    @Autowired
    MutantService(DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public Boolean isMutant(DNARequest dnaRequest) {
        List<SequenceFinder> sequenceFinders = getSequenceFinders();
        DNA dnaToAnalyze = new DNA(dnaRequest.getDna());
        dnaToAnalyze.determineAnomaly(sequenceFinders);
        dnaRepository.save(dnaToAnalyze);
        return dnaToAnalyze.isMutant();
    }

    private List<SequenceFinder> getSequenceFinders() {
        List<SequenceFinder> sequenceFinders = new ArrayList<>();

        RightSequenceFinder rightSequenceFinder = new RightSequenceFinder(4);
        sequenceFinders.add(rightSequenceFinder);

        DownSequenceFinder downSequenceFinder = new DownSequenceFinder(4);
        sequenceFinders.add(downSequenceFinder);

        RightDownSequenceFinder rightDownSequenceFinder = new RightDownSequenceFinder(4);
        sequenceFinders.add(rightDownSequenceFinder);

        return sequenceFinders;
    }

}
