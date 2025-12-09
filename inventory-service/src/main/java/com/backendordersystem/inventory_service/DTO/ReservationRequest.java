package com.backendordersystem.inventory_service.DTO;

public record ReservationRequest(
    String orderId,
    String sku,
    Integer qty
) {}
