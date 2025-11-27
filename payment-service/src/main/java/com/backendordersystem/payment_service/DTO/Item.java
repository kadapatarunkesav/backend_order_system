package com.backendordersystem.payment_service.DTO;

public record Item(
    String sku, 
    int qty, 
    long price) {
        
    }
