package com.backendordersystem.notification_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.backendordersystem.notification_service.Entity.OutBoxEvent;
import com.backendordersystem.notification_service.Service.NotificationService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class NotificationEventConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "payment.success", groupId = "notification-service")
    public void sendNotificationMail(String message) {

        try {
            OutBoxEvent outBoxEvent = objectMapper.readValue(message, OutBoxEvent.class);
            if ("PAYMENT_SUCCESS".equals(outBoxEvent.getType())){
                notificationService.sendMail(message);
            }
        } catch (Exception e) {
        }
    }

}
