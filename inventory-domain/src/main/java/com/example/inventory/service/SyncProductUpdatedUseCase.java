package com.example.inventory.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.repository.InventoryProductRepository;

public class SyncProductUpdatedUseCase {

    private final InventoryProductRepository repository;

    public SyncProductUpdatedUseCase(InventoryProductRepository repository) {
        this.repository = repository;
    }

    public InventoryProduct execute(String productId) {
        // We only know the productId from the event, so we ensure the entry exists.
        return repository.findByProductId(productId)
                .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.ACTIVE)));
    }
}

