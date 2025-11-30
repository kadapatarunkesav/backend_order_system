package com.backendordersystem.order_service.Kafka;

import java.time.Instant;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backendordersystem.order_service.Entity.OutBoxEvent;
import com.backendordersystem.order_service.Repository.OutBoxRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableScheduling
public class OutboxPublisherScheduler {

    private final OutBoxRepo outBoxRepo;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void publishEvents() {

        List<OutBoxEvent> events = outBoxRepo.findTop20ByPublishedFalseOrderByCreatedAtAsc();

        for (OutBoxEvent event : events) {
            kafkaTemplate.send("order.events", event.getAggregateId().toString(), event.getPayload())
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
