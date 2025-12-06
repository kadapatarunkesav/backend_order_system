package com.backendordersystem.order_service.Repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.order_service.Entity.Order;

public interface OrderRepo extends JpaRepository<Order, UUID> {

    Page<Order> findByUserId(String userId, Pageable pageable); }

