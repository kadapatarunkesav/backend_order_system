package com.backendordersystem.inventory_service.Service;


import org.springframework.stereotype.Service;

import com.backendordersystem.inventory_service.DTO.ProductRequest;
import com.backendordersystem.inventory_service.Entity.Product;
import com.backendordersystem.inventory_service.Repository.ProductRepo;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepo productRepo;
    private final ObjectMapper objectMapper;

    public String addProduct(ProductRequest entity) throws Exception{
        Product newProduct = new Product();

        newProduct.setSku(entity.sku());
        newProduct.setName(entity.name());
        newProduct.setDescription(entity.description());
        newProduct.setActive(true);
        newProduct.setPrice(entity.price());

        productRepo.save(entity);

        return objectMapper.writeValueAsString(newProduct);

    }

}
