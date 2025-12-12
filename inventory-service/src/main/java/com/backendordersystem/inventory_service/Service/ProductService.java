package com.backendordersystem.inventory_service.Service;


import java.util.List;

import org.springframework.stereotype.Service;

import com.backendordersystem.inventory_service.DTO.ListOfProducts;
import com.backendordersystem.inventory_service.DTO.ProductRequest;
import com.backendordersystem.inventory_service.DTO.ProductResponse;
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
        newProduct.setPrice(entity.price());
        newProduct.setActive(true);

        productRepo.save(newProduct);

        return objectMapper.writeValueAsString(newProduct);
    }

    public List<Product> createMultipleProducts(ListOfProducts entity) {

        List<Product> productsList=entity.listOfProducts().stream().map(p->{
            Product newProduct = new Product();
            newProduct.setActive(true);
            newProduct.setDescription(p.description());
            newProduct.setPrice(p.price());
            newProduct.setName(p.name());
            newProduct.setSku(p.sku());
            
            return newProduct;
        }).toList();   

        return productsList;1
}
