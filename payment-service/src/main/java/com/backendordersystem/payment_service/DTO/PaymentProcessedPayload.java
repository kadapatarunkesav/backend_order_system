package com.backendordersystem.payment_service.DTO;

import java.util.UUID;

public record PaymentProcessedPayload(UUID orderId,
    UUID paymentId,
    String status,
    String gatewayTxnId) {

       }
