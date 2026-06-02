package com.example.inventory.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.repository.InventoryProductRepository;

import java.math.BigDecimal;

public class ReplenishStockUseCase {

    private final InventoryProductRepository repository;

    public ReplenishStockUseCase(InventoryProductRepository repository) {
        this.repository = repository;
    }

    public InventoryProduct execute(String productId, BigDecimal quantity) {
        if (quantity == null || quantity.signum() <= 0) {
            return repository.findByProductId(productId)
                    .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.ACTIVE)));
        }

        InventoryProduct product = repository.findByProductId(productId)
                .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.ACTIVE)));

        product.replenish(quantity);
        return repository.save(product);
    }
}

