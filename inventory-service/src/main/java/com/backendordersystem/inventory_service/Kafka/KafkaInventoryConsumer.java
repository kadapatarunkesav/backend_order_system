package com.backendordersystem.inventory_service.Kafka;

import java.util.List;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.backendordersystem.inventory_service.DTO.ItemStockDto;
import com.backendordersystem.inventory_service.Service.InventoryService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class KafkaInventoryConsumer {

    private final ObjectMapper objectMapper;
    private final InventoryService inventoryService;

    @KafkaListener(topics = "inventory.inquriy", groupId = "inventory-service")
    public void productInquriy(String message) throws Exception {

        try {
            List<ItemStockDto> items = objectMapper.readValue(
                    message,
                    new TypeReference<List<ItemStockDto>>() {
                    });
                    inventoryService.productInquriy(items);
        } catch (Exception e) {
            
        }
    }
}
