package com.backendordersystem.order_service.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "idempotency_keys")
public class IdempotencyKey {

    @Id
    private String key;

    @Column(nullable = false)
    private UUID orderId;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}
