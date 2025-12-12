package com.backendordersystem.inventory_service.DTO;

import java.util.List;

public record  ListOfProducts(
    List<ProductRequest> listOfProducts
) {



}
