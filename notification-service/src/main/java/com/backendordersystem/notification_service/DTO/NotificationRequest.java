package com.backendordersystem.notification_service.DTO;

import java.util.UUID;

public record NotificationRequest(
        UUID correlationId,
        String to,
        String subject,
        String body) {

}
