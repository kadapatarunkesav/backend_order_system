package com.backendordersystem.payment_service.DTO;

import java.time.Instant;
import java.util.UUID;

public record OrderCreatedEvent(
    UUID eventId, 
    String eventType, 
    Instant occurredAt, 
    UUID correlationId, 
    OrderPayload payload) {
        
    }
