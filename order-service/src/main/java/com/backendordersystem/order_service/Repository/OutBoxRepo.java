package com.backendordersystem.order_service.Repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.order_service.Entity.OutBoxEvent;

public interface OutBoxRepo extends JpaRepository<OutBoxEvent,UUID>{

    List<OutBoxEvent> findTop20ByPublishedFalseOrderByCreatedAtAsc();

}
