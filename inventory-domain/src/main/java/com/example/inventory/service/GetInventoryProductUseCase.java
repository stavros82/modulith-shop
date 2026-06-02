package com.example.inventory.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.repository.InventoryProductRepository;

public class GetInventoryProductUseCase {

    private final InventoryProductRepository repository;

    public GetInventoryProductUseCase(InventoryProductRepository repository) {
        this.repository = repository;
    }

    public InventoryProduct execute(String productId) {
        return repository.findByProductId(productId)
                .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.ACTIVE)));
    }
}

