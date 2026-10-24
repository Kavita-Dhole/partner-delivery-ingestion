package com.example.ingestion;

import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DeliveryController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public DeliveryController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    // POST /deliveries/{partnerId}  with any text body -> published to Kafka
    // The partnerId is the message key, so one partner's deliveries stay in order.
    @PostMapping("/deliveries/{partnerId}")
    public ResponseEntity<String> publish(@PathVariable String partnerId,
                                          @RequestBody String payload) {
        kafkaTemplate.send(IngestionApplication.TOPIC, partnerId, payload);
        return ResponseEntity.accepted().body("Delivery from " + partnerId + " accepted");
    }
}
