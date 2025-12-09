package com.backendordersystem.inventory_service.Entity;


import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;





@Entity
@Table(name = "processed_events")
public class ProcessedEvent {
    @Id
    private String eventId;

    private Instant processedAt;
}

