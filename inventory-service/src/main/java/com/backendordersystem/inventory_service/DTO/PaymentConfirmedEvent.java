package com.backendordersystem.inventory_service.DTO;

public record PaymentConfirmedEvent(
    String orderId,
    String paymentId,
    String userId
) {}

