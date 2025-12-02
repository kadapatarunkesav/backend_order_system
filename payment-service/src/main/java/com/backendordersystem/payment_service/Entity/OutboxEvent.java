package com.backendordersystem.payment_service.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "outbox")
public class OutboxEvent {
    
    @Id
    @GeneratedValue
    private UUID id;

    // added for identity of order
    private String aggregateType;
    private UUID  aggregateId;

    @Column(nullable = false, columnDefinition = "json")
    private String payload;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private boolean published;

    @Column(nullable = false)
    private Instant createdAt;

    private Instant publishedAt;

    @PrePersist
    public void prePersist() {
        if (id == null) id = UUID.randomUUID();
        if (createdAt == null) createdAt = Instant.now();
        published = false;
    }
}

