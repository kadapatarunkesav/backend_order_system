package com.backendordersystem.payment_service.Service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.backendordersystem.payment_service.DTO.OrderPayload;
import com.backendordersystem.payment_service.Entity.OutboxEvent;
import com.backendordersystem.payment_service.Entity.Payment;
import com.backendordersystem.payment_service.Repository.OutBoxRepo;
import com.backendordersystem.payment_service.Repository.PaymentRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PaymentService {

    private final PaymentRepo paymentRepo;
    private final ObjectMapper objectMapper;
    private final OutBoxRepo outBoxRepo;


    @Transactional
    public void processPayment(OutboxEvent event) throws Exception{

        OrderPayload orderPayload = objectMapper.readValue(event.getPayload(), OrderPayload.class);
        Payment payment = new Payment();
        payment.setAmount(orderPayload.totalAmount());
        payment.setOrderId(orderPayload.orderId());
        payment.setStatus("PENDING");

        paymentRepo.save(payment);
    }    
}