package com.backendordersystem.inventory_service.DTO;

import java.util.List;

public record InventoryUpdatedEvent(
    String orderId,
    List<ItemStockDto> items,
    String status 
) {}

