package com.backendordersystem.inventory_service.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendordersystem.inventory_service.Entity.Product;

public interface ProductRepo extends JpaRepository<Product,Long>{

}
