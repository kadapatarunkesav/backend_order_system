package com.backendordersystem.inventory_service.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backendordersystem.inventory_service.DTO.ItemStockDto;
import com.backendordersystem.inventory_service.Repository.StockRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryService {

    private final StockRepo stockRepo;
    private final ObjectMapper objectMapper;

    public String productInquriy(List<ItemStockDto> items) throws Exception {

        List<ItemStockDto> responseList = items.stream().filter(item -> {
            Integer quantity = stockRepo.findQuantityAvailableBySku(item.sku());
            return quantity == null && quantity < item.qty();
        })
                .map(item -> new ItemStockDto(item.sku(), item.qty()))
                .collect(Collectors.toList());
        if (!responseList.isEmpty()) {
            String response = objectMapper.writeValueAsString(responseList);
            return response;
        } else {
            productReservation(items);
            return "Successfully added";
        }
    }

    @Transactional
    private String  productReservation(List<ItemStockDto> items) {

        
    }

}