package com.backendordersystem.inventory_service.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;




@Entity
@Table(name = "stock")
@Data
public class Stock {
    
    @Id
    private String sku;

    private Integer quantityAvailable;
}