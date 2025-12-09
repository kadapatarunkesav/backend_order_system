package com.backendordersystem.inventory_service.Controller;

import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.inventory_service.Repository.ProductRepo;
import com.backendordersystem.inventory_service.Service.ProductService;

import lombok.AllArgsConstructor;

import org.apache.kafka.common.requests.ProduceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    

    @PostMapping("/add")
    public ResponseEntity<String> postMethodName(@RequestBody ProduceRequest entity) {
        String res = productService.addProduct(entity);
        return ResponseEntity.ok(res);
    }
    
}
