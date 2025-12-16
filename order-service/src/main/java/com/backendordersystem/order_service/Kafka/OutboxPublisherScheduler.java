package com.backendordersystem.order_service.Kafka;

import java.time.Instant;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backendordersystem.order_service.DTO.ItemStockDto;
import com.backendordersystem.order_service.Entity.OutBoxEvent;
import com.backendordersystem.order_service.Repository.OutBoxRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableScheduling
public class OutboxPublisherScheduler {

    private final OutBoxRepo outBoxRepo;
    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Scheduled(fixedDelay = 1000)
    public void publishEvents() throws Exception{

        List<OutBoxEvent> events = outBoxRepo.findTop20ByPublishedFalseOrderByCreatedAtAsc();
        for (OutBoxEvent event : events) {

            String json = objectMapper.writeValueAsString(event);

            kafkaTemplate.send("order.events", event.getAggregateId().toString(), json)
                    .whenComplete((result, ex) -> {

                        if (ex == null) {
                            event.setPublished(true);
                            event.setPublishedAt(Instant.now());
                            outBoxRepo.save(event);
                        }
                    });
        }
    }

    public void kafkaItemsAvaiability(List<ItemStockDto> stockDtos)throws Exception{

        String json = objectMapper.writeValueAsString(stockDtos);

        kafkaTemplate.send("inventory.inquriy","",json);

    }

}
