package com.backendordersystem.order_service.ServiceLayer;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import static org.springframework.data.domain.Sort.by;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendordersystem.order_service.DTO.OrderItemResponse;
import com.backendordersystem.order_service.DTO.OrderPageableResponse;
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

        Order newOrder = new Order();
        newOrder.setOrderId(UUID.randomUUID());
        newOrder.setUserId(UUID.randomUUID());
        newOrder.setStatus("CREATED");
        newOrder.setTotalAmount(totalAmount);
        newOrder.setCreatedAt(Instant.now());
        newOrder.setUpdatedAt(Instant.now());

        List<OrderItem> orderItems = orderRequest.items().stream().map(i -> {
            OrderItem item = new OrderItem();
            item.setOrder(newOrder);
            item.setSku(i.sku());
            item.setQty(i.qty());
            item.setPrice(i.price());
            return item;
        }).toList();

        newOrder.setItems(orderItems);

        Order saved = orderRepo.save(newOrder);

        OrderResponse response = new OrderResponse(
                saved.getOrderId(),
                saved.getStatus(),
                saved.getTotalAmount());

        return response;
    }

    public List<OrderItemResponse> getOrderDetailsById(UUID orderId, String idempotencyKey) {

        Order getOrder = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        List<OrderItemResponse> itemResponses = getOrder.getItems()
                .stream()
                .map(i -> new OrderItemResponse(
                        i.getSku(),
                        i.getQty(),
                        i.getPrice()))
                .toList();

        return itemResponses;
    }

    public Page<OrderPageableResponse> getOrdersByUser(UUID userId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size, by("createdAt").descending());

        Page<Order> orders = orderRepo.findByUserId(userId, pageable);

        return orders.map(order -> new OrderPageableResponse(
                order.getOrderId(),
                order.getStatus(),
                order.getTotalAmount(),
                order.getItems().stream()
                        .map(i -> new OrderItemResponse(i.getSku(), i.getQty(), i.getPrice()))
                        .toList()));
    }


    // used pageable response DTO for viewing purpose --
    // Mulitple use of one DTO Obj

    public OrderPageableResponse cancelOrder(UUID orderId) {
        Order order = orderRepo.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        if (!order.getStatus().equals("CREATED")) {
            throw new RuntimeException("Only CREATED orders can be cancelled ");
        }

        order.setStatus("CANCELLED");
        orderRepo.save(order);

        List<OrderItemResponse> items = order.getItems()
                .stream()
                .map(i -> new OrderItemResponse(i.getSku(), i.getQty(), i.getPrice()))
                .toList();

        return new OrderPageableResponse(
                order.getOrderId(),
                order.getStatus(),
                order.getTotalAmount(),
                items);
    }

}
