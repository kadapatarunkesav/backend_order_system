package com.backendordersystem.inventory_service.DTO;

public record ProductResponse(
    String sku,
    String name,
    String description,
    boolean active,
    Integer quantityAvailable
) {}
