package com.meli.magneto.services;

import com.meli.magneto.exception.InvalidParametersException;
import com.meli.magneto.exception.NonMutantException;
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
import java.util.stream.Stream;

@Service
public class MutantService {
    private static String NITROGENOUS_BASE_PATTERN = "[A|T|C|G]+";

    private DNARepository dnaRepository;

    @Autowired
    MutantService(DNARepository dnaRepository) {
        this.dnaRepository = dnaRepository;
    }

    public void isMutant(DNARequest dnaRequest) throws NonMutantException {
        checkDNASequence(dnaRequest.getDna());
        List<SequenceFinder> sequenceFinders = getSequenceFinders();
        DNA dnaToAnalyze = new DNA(dnaRequest.getDna());
        dnaToAnalyze.determineAnomaly(sequenceFinders);
        dnaRepository.save(dnaToAnalyze);
        if (!dnaToAnalyze.isMutant())
            throw new NonMutantException();
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

    private void checkDNASequence(String[] dna) throws InvalidParametersException {
        Integer dnaLength = dna.length;
        Stream<String> stream = Stream.of(dna);
        stream.forEach(s -> {
            if (s.length() != dnaLength || !s.matches(NITROGENOUS_BASE_PATTERN))
                throw new InvalidParametersException();
        });
    }

}
