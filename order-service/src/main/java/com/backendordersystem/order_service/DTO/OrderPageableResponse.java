package com.backendordersystem.order_service.DTO;

import java.util.List;
import java.util.UUID;

public record OrderPageableResponse(  
        UUID orderId,
        String status,
        Long totalAmount,
        List<OrderItemResponse> items
) {}