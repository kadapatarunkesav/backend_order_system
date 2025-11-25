package com.backendordersystem.order_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.order_service.Entity.IdempotencyKey;

public interface IdempotencyKeyRepo extends JpaRepository <IdempotencyKey, String> {
}
