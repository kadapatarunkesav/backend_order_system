package com.backendordersystem.payment_service.Kafka;

import java.time.Instant;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backendordersystem.payment_service.Entity.OutboxEvent;
import com.backendordersystem.payment_service.Repository.OutBoxRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableScheduling
public class OutBoxPublisher {

    private final OutBoxRepo outBoxRepo;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void publishEvents() throws Exception {

        List<OutboxEvent> outBoxEvents = outBoxRepo
                .findTop20ByPublishedFalseAndTypeOrderByCreatedAtAsc("PAYMENT_FAILED");

        for (OutboxEvent event : outBoxEvents) {
            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send("payment.failed", event.getAggregateId().toString(), json)
                    .whenComplete((result, ex) -> {
                        if (ex == null) {
                            System.out.println(event.getType()+event.getAggregateType());
                            event.setPublished(true);
                            event.setPublishedAt(Instant.now());
                            outBoxRepo.save(event);
                        }
                    });
        }
    }

    @Scheduled(fixedDelay = 1000)
    public void publishSucessEvents() throws Exception {
        List<OutboxEvent> outBoxEvents = outBoxRepo
                .findTop20ByPublishedFalseAndTypeOrderByCreatedAtAsc("PAYMENT_SUCCESS");

        for (OutboxEvent event : outBoxEvents) {
            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send("paymnet.success", event.getAggregateId().toString(), json)
                    .whenComplete((result, ex) -> {
                        if (ex == null) {
                            event.setPublished(true);
                            event.setPublishedAt(Instant.now());
                            outBoxRepo.save(event);
                        }
                    });
        }
    }
}
