package com.backendordersystem.inventory_service.DTO;

import java.util.List;

public record InventoryUpdatedEvent(
    String orderId,
    List<ItemDto> items,
    String status 
) {}

