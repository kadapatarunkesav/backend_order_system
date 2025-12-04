package com.backendordersystem.payment_service.Service;

import java.util.UUID;

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


    public void outBoxFailedPayment(Payment payment) {
        OutboxEvent  outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(payment.getOrderId());
        outboxEvent.setPayload("""
                               {
                               }""");
        outboxEvent.setType("PAYMENT_FAILED");
        outboxEvent.setAggregateType("NONE");

        payment.setStatus("FAILED");
        
        outBoxRepo.save(outboxEvent);
    }    

    public void paymentTransaction(UUID orderId , Long amount,String paymentMode) throws Exception{
        Payment payment = paymentRepo.findByOrderId(orderId).orElseThrow();
        if(amount.equals(payment.getAmount()) && payment.getStatus().equals("PENDING"))
            payment.setStatus("CONFIRMED");
        OutboxEvent  outboxEvent = new OutboxEvent();
        outboxEvent.setAggregateId(orderId);
        outboxEvent.setPayload("""
                               {
                               }""");
        outboxEvent.setType("PAYMENT_SUCCESS");
        outboxEvent.setAggregateType(paymentMode);

        payment.setStatus("SUCCESS");
        
        outBoxRepo.save(outboxEvent);
        paymentRepo.save(payment);
    }
}