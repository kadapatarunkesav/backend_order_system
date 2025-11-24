package com.backendordersystem.order_service.ServiceLayer;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendordersystem.order_service.DTO.OrderRequest;
import com.backendordersystem.order_service.DTO.OrderResponse;
import com.backendordersystem.order_service.Entity.Order;
import com.backendordersystem.order_service.Entity.OrderItem;
import com.backendordersystem.order_service.Repository.OrderRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class OrderServices {

    private final OrderRepo orderRepo;

    @Transactional
    public OrderResponse createOrder(OrderRequest orderRequest, String idempotencyKey) {

        long totalAmount = orderRequest.items()
                .stream()
                .mapToLong(item -> item.price() * item.qty())
                .sum();

        Order newOrder =new Order();
        newOrder.setOrderId(UUID.randomUUID());
        newOrder.setUserId(UUID.randomUUID());
        newOrder.setStatus("CREATED");
        newOrder.setTotalAmount(totalAmount);
        newOrder.setCreatedAt(Instant.now());
        newOrder.setUpdatedAt(Instant.now());


        List<OrderItem> items = orderRequest.items().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setOrder(newOrder);
            item.setSku(i.sku());
            item.setQty(i.qty());
            item.setPrice(i.price());
            return item;
        }).toList();


        newOrder.setItems(items);

        Order saved = orderRepo.save(newOrder);

        OrderResponse response= new OrderResponse(
            saved.getOrderId(),
            saved.getStatus(),
            saved.getTotalAmount());

        return response;
    }

}
