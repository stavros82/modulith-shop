package com.example.inventory.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.repository.InventoryProductRepository;

import java.math.BigDecimal;

public class QuarantineProductOnQualityIssueUseCase {

    private final InventoryProductRepository repository;

    public QuarantineProductOnQualityIssueUseCase(InventoryProductRepository repository) {
        this.repository = repository;
    }

    public InventoryProduct execute(String productId) {
        InventoryProduct product = repository.findByProductId(productId)
                .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.UNSAFE)));

        product.markUnsafeAndQuarantineAll();
        // If product entry was newly created, its stock is 0; quarantine all is still consistent.
        return repository.save(product);
    }
}

