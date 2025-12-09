package com.backendordersystem.inventory_service.DTO;

public record StockUpdateRequest(
    String sku,
    Integer quantity
) {}
