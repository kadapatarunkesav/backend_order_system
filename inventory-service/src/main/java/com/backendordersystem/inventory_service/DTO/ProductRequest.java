package com.backendordersystem.inventory_service.DTO;

public record ProductRequest(
    String sku,
    String name,
    String description,
    boolean active,
    Long price
) {}
