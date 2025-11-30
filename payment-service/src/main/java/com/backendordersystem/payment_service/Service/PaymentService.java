package com.backendordersystem.payment_service.Service;

import org.springframework.stereotype.Service;

import com.backendordersystem.payment_service.DTO.OrderCreatedEvent;
import com.backendordersystem.payment_service.DTO.OrderPayload;
import com.backendordersystem.payment_service.Repository.PaymentRepo;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;

    public void processPayment(OrderCreatedEvent event) {

        OrderPayload orderPayload = event.payload();
        if (paymentRepo.existsByOrderId(orderPayload.orderId())) {
            System.out.println(orderPayload);
        }
    }

}