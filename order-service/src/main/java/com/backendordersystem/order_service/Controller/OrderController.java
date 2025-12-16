package com.backendordersystem.order_service.Controller;

import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.order_service.DTO.OrderItemResponse;
import com.backendordersystem.order_service.DTO.OrderPageableResponse;
import com.backendordersystem.order_service.DTO.OrderRequest;
import com.backendordersystem.order_service.ServiceLayer.OrderServices;
import com.fasterxml.jackson.core.JsonProcessingException;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/orderService/v1")
@AllArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping("/createOrder")
    public ResponseEntity<String> postMethodName(@RequestBody OrderRequest orderRequest,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

        String response = orderServices.createOrder(orderRequest, idempotencyKey);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/getOrderDetailsById/{orderId}")
    public ResponseEntity<List<OrderItemResponse>> getMethodName(@PathVariable UUID orderId,
            @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {
        List<OrderItemResponse> response = orderServices.getOrderDetailsById(orderId, idempotencyKey);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getOrder")
    public String getMethodName(@RequestParam String param) {
        return new String();
    }

    @GetMapping("/ordersByUserId/{userId}")
    public ResponseEntity<Page<OrderPageableResponse>> getMyOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @PathVariable String userId) {
        Page<OrderPageableResponse> response = orderServices.getOrdersByUser(userId, page, size);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/cancleOrderById/{orderId}")
    public ResponseEntity<OrderPageableResponse> cancelOrder(@PathVariable UUID orderId) throws JsonProcessingException {

        OrderPageableResponse response = orderServices.cancelOrder(orderId);

        return ResponseEntity.ok(response);
    }

}
