package com.backendordersystem.inventory_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.inventory_service.Entity.Stock;

public interface StockRepo extends JpaRepository<Stock, String>{

    Integer findQuantityAvailableBySku(String sku);

}
