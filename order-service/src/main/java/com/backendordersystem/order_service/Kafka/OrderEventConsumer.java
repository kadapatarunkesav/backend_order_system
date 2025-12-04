package com.backendordersystem.order_service.Kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.backendordersystem.order_service.Entity.OutBoxEvent;
import com.backendordersystem.order_service.ServiceLayer.OrderServices;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class OrderEventConsumer {

    private final ObjectMapper objectMapper;
    private final OrderServices orderServices;

    @KafkaListener(topics="payment.failed",groupId="order-service")
    public void consumeFailedPayment(String message) throws Exception{
    
        try{
            OutBoxEvent event = objectMapper.readValue(message, OutBoxEvent.class);
            if(event.getType().equals("PAYMENT_FAILED")){
                orderServices.paymentFailed(event.getType(),event.getAggregateId());
            }
        }catch(Exception e){

        }
    }
}
