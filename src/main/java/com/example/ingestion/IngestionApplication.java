package com.example.ingestion;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

@SpringBootApplication
public class IngestionApplication {

    public static final String TOPIC = "partner-deliveries";

    public static void main(String[] args) {
        SpringApplication.run(IngestionApplication.class, args);
    }

    // Creates the topic on startup if it does not exist yet
    @Bean
    NewTopic partnerDeliveriesTopic() {
        return TopicBuilder.name(TOPIC).partitions(3).replicas(1).build();
    }
}
