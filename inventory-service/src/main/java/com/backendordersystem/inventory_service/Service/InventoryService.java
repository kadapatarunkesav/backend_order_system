package com.backendordersystem.inventory_service.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backendordersystem.inventory_service.DTO.ItemDto;
import com.backendordersystem.inventory_service.Repository.StockRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class InventoryService {

    private final StockRepo stockRepo;
    private final ObjectMapper objectMapper;

    public String productInquriy(List<ItemDto> items) throws Exception {

        List<ItemDto> responseList = items.stream().filter(item -> {
            Integer quantity = stockRepo.findQuantityAvailableById(item.sku());
            return quantity != null && quantity > item.qty();
        })
                .map(item -> new ItemDto(item.sku(), item.qty()))
                .collect(Collectors.toList());
        if (responseList.size() > 0) {
            String response = objectMapper.writeValueAsString(responseList);
            return response;
        } else {
            productReservation(items);
        }
        return "Successfully added";
    }

    private void productReservation(List<ItemDto> items) {

    }

}