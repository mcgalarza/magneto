package com.meli.magneto.services;

import com.meli.magneto.model.DNA;
import com.meli.magneto.model.DNARequest;
import com.meli.magneto.mq.MqConfiguration;
import com.meli.magneto.repository.DNARepository;
import com.meli.magneto.strategies.DownSequenceFinder;
import com.meli.magneto.strategies.RightDownSequenceFinder;
import com.meli.magneto.strategies.RightSequenceFinder;
import com.meli.magneto.strategies.SequenceFinder;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MutantService {

    private DNARepository dnaRepository;
    private RabbitTemplate rabbitTemplate;

    @Autowired
    MutantService(DNARepository dnaRepository, RabbitTemplate rabbitTemplate) {
        this.dnaRepository = dnaRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    @CacheEvict(value = "stats", key = "'_stat'")
    public Boolean isMutant(DNARequest dnaRequest) {
        List<SequenceFinder> sequenceFinders = getSequenceFinders();
        DNA dnaToAnalyze = new DNA(dnaRequest.getDna());
        dnaToAnalyze.determineAnomaly(sequenceFinders);
        rabbitTemplate.convertAndSend(MqConfiguration.QUEUE_NAME, dnaToAnalyze);
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
