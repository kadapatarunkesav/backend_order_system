package com.backendordersystem.payment_service.Kafka;

import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backendordersystem.payment_service.Entity.OutboxEvent;
import com.backendordersystem.payment_service.Repository.OutBoxRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableScheduling
public class OutBoxPublisher {

    private OutBoxRepo outBoxRepo;
    private KafkaTemplate<String,String>kafkaTemplate;

    @Scheduled(fixedDelay=1000)
    public void publishEvents(){
    List <OutboxEvent> outBoxEvents= outBoxRepo.findTop20ByPublishedFalseOrderByCreatedAtAsc();

    }
}
