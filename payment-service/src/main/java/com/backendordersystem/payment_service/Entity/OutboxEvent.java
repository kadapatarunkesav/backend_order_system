package com.backendordersystem.payment_service.Entity;

import java.time.Instant;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "outbox")
public class OutboxEvent {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String aggregateType;
    private UUID  aggregateId;
    private String eventType;

    
    @Column(columnDefinition = "jsonb")
    private String payload;
    private boolean published = false;
    private Instant createdAt;
    private Instant publishedAt;
    @PrePersist
    public void onCreate(){ createdAt = Instant.now(); }

}

