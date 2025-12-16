package com.backendordersystem.inventory_service.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.inventory_service.DTO.ProductRequest;
import com.backendordersystem.inventory_service.Service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ObjectMapper objectMapper;
    

    @PostMapping("/add")
    public ResponseEntity<String> addProducts(@RequestBody ProductRequest entity) throws Exception {
        String res = productService.addProduct(entity);
        // String res= objectMapper.writeValueAsString(entity);
        return ResponseEntity.ok(res);
    }
    
}
