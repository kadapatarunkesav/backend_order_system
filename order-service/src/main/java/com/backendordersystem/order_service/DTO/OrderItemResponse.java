package com.backendordersystem.order_service.DTO;

public record OrderItemResponse(
        String sku,
        int qty,
        long price
) {}