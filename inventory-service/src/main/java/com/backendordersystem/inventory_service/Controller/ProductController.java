package com.backendordersystem.inventory_service.Controller;

import java.util.List;

import org.apache.kafka.common.requests.ProduceResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendordersystem.inventory_service.DTO.ListOfProducts;
import com.backendordersystem.inventory_service.DTO.ProductRequest;
import com.backendordersystem.inventory_service.DTO.ProductResponse;
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
    


    @PostMapping("/addProducts")
    public ResponseEntity<ListOfProducts> addMultipleProducts(@RequestBody ListOfProducts entity) throws Exception {
        String res= objectMapper.writeValueAsString(entity);
        
        ListOfProducts response = productService.createMultipleProducts(entity);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
