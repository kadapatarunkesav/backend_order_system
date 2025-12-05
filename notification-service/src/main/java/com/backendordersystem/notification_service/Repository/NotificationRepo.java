package com.backendordersystem.notification_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.notification_service.Entity.Notification;

public interface NotificationRepo extends JpaRepository<Notification, Long>{

}
