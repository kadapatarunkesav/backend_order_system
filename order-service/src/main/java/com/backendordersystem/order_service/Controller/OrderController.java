package com.backendordersystem.order_service.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.order_service.DTO.OrderRequest;
import com.backendordersystem.order_service.DTO.OrderResponse;
import com.backendordersystem.order_service.ServiceLayer.OrderServices;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/orderService/v1")
@AllArgsConstructor
public class OrderController {

    private final OrderServices orderServices;

    @PostMapping("/createOrder")
    public ResponseEntity<OrderResponse> postMethodName(@RequestBody OrderRequest orderRequest,
        @RequestHeader(value = "Idempotency-Key", required = false) String idempotencyKey) {

            OrderResponse response = orderServices.createOrder(orderRequest, idempotencyKey);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    

}
