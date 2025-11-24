package com.backendordersystem.order_service.DTO;


public record OrderItemRequest(
        String sku,
        int qty,
        long price
) {}