package com.backendordersystem.notification_service.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.notification_service.Entity.OutBoxEvent;

public interface OutBoxRepo extends JpaRepository<OutBoxEvent, UUID>{

}
