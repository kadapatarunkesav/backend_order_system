package com.backendordersystem.payment_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.backendordersystem.payment_service.DTO.OrderCreatedEvent;
import com.backendordersystem.payment_service.Service.PaymentService;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;


@Component
@AllArgsConstructor
public class PaymentEventConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @KafkaListener(topics = "order.events", groupId = "payment-service")
    public void consume(String message) {

        try {
            JsonNode root = objectMapper.readTree(message);

            String eventType = root.get("status").asText();

            if (eventType.equals("CREATED")) {
                OrderCreatedEvent event =
                objectMapper.treeToValue(root.get("data"), OrderCreatedEvent.class);
                paymentService.processPayment(event);
            }

        } catch (Exception e) {
            
        }
    }

}
