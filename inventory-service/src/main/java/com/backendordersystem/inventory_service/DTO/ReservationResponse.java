package com.backendordersystem.inventory_service.DTO;

import com.backendordersystem.inventory_service.Entity.ReservationStatus;

public record ReservationResponse(
    String reservationId,
    String orderId,
    String sku,
    Integer qty,
    ReservationStatus status
) {}
