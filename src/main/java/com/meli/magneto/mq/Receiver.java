package com.meli.magneto.mq;

import com.meli.magneto.model.DNA;
import com.meli.magneto.repositories.DNARepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    private Logger logger = LogManager.getLogger(Receiver.class);

    @Autowired
    private DNARepository dnaRepository;

    @RabbitListener(queues = MqConfiguration.QUEUE_NAME)
    public void onMessageFromRabbitMQ(final DNA dna){
        logger.info("Received message of dna to save with id: " + dna.getId() + " - dna: " + dna.getDnaSequence());
        dnaRepository.save(dna);
    }

}
