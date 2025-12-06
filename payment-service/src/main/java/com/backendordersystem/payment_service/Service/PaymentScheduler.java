package com.backendordersystem.payment_service.Service;

import java.time.Instant;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.backendordersystem.payment_service.Entity.Payment;
import com.backendordersystem.payment_service.Repository.OutBoxRepo;
import com.backendordersystem.payment_service.Repository.PaymentRepo;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
@EnableScheduling
public class PaymentScheduler {

    private final PaymentService paymentService;
    private final PaymentRepo paymentRepo;
    private final OutBoxRepo outBoxRepo;

    @Scheduled(fixedDelay = 1000)
    public void paymentProccessor() throws  Exception{

        List<Payment> pendingPayList = paymentRepo.findTop20ByStatusOrderByCreatedAtAsc("PENDING");

        for (Payment payee : pendingPayList) {
            if (payee.getCreatedAt().plusSeconds(600).isBefore(Instant.now())) {

                payee.setStatus("FAILED");
                payee.setUpdatedAt(Instant.now());
                paymentService.outBoxFailedPayment(payee);
            }
        }
        paymentRepo.saveAll(pendingPayList);
    }
}
