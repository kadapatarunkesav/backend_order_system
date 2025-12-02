package com.backendordersystem.payment_service.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.payment_service.Entity.OutboxEvent;

public interface OutBoxRepo extends JpaRepository<OutboxEvent,UUID>{

    List<OutboxEvent> findTop20ByPublishedFalseOrderByCreatedAtAsc();

}
