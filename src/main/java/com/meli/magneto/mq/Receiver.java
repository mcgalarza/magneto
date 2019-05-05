package com.meli.magneto.mq;

import com.meli.magneto.model.DNA;
import com.meli.magneto.repository.DNARepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Receiver {

    @Autowired
    private DNARepository dnaRepository;

    @RabbitListener(queues = MqConfiguration.QUEUE_NAME)
    public void onMessageFromRabbitMQ(final DNA dna){
        dnaRepository.save(dna);
    }

}
