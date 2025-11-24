package com.backendordersystem.order_service.DTO;

import java.util.UUID;


public record OrderResponse(
        UUID orderId,
        String status,
        long totalAmount
) {}
