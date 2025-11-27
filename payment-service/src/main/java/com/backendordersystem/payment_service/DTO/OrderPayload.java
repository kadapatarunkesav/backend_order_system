package com.backendordersystem.payment_service.DTO;

import java.util.List;
import java.util.UUID;

public record OrderPayload(
    UUID orderId, 
    UUID userId, 
    List<Item> items,
    Long totalAmount,
    String paymentMethod) {
        
    }
