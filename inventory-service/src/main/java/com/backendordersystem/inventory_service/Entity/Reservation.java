package com.backendordersystem.inventory_service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String reservationId;

    private String orderId;
    private String sku;
    private Integer qty;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status; // RESERVED, FAILED
}

