package com.backendordersystem.payment_service.Controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.payment_service.Service.PaymentService;

import lombok.AllArgsConstructor;



@RestController
@AllArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/payment/{amount}/order{orderId}/mode{paymentMode}")
    public ResponseEntity<String> postMethodName(@PathVariable Long amount,@PathVariable UUID orderId,@PathVariable String paymentMode) {

        try {
            paymentService.paymentTransaction(orderId, amount,paymentMode);
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT).body("TimeOut");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Successful Transaction");
    }
    
}
