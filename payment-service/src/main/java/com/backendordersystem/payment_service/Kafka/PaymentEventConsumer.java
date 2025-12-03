package com.backendordersystem.payment_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.backendordersystem.payment_service.Entity.OutboxEvent;
import com.backendordersystem.payment_service.Service.PaymentService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PaymentEventConsumer {

    private final ObjectMapper objectMapper;
    private final PaymentService paymentService;

    @KafkaListener(topics = "order.events", groupId = "payment-service")
    public void consume(String message) throws Exception {

        try {
            OutboxEvent event = objectMapper.readValue(message, OutboxEvent.class);
            if (event.getType().equals("ORDER_CREATED")) {
                paymentService.processPayment(event);
            }
        } catch (Exception e) {

        }
    }

}
