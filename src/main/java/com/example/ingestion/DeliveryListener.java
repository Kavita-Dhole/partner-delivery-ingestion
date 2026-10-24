package com.example.ingestion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class DeliveryListener {

    private static final Logger log = LoggerFactory.getLogger(DeliveryListener.class);

    @KafkaListener(topics = IngestionApplication.TOPIC)
    public void onDelivery(String payload) {
        // Next step: validate here, send bad deliveries to a dead-letter topic
        log.info("Received delivery: {}", payload);
    }
}
