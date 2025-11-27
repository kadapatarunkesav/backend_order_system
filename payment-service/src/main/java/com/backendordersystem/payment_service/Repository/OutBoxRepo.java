package com.backendordersystem.payment_service.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.payment_service.Entity.OutboxEvent;

public interface OutBoxRepo extends JpaRepository<OutboxEvent,UUID>{

}
