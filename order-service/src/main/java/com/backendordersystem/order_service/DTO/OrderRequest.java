package com.backendordersystem.order_service.DTO;

import java.util.List;

public record OrderRequest(

    List<OrderItemRequest> items

) {

}
