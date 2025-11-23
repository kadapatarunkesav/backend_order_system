package com.backendordersystem.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.order_service.Entity.OutBoxedEvent;

public interface OutboxEventRepository extends JpaRepository<OutBoxedEvent, Long> { 
    
}
