package com.example.inventory.repository;

import com.example.inventory.model.InventoryProduct;
import java.util.Optional;

public interface InventoryProductRepository {

    Optional<InventoryProduct> findByProductId(String productId);

    InventoryProduct save(InventoryProduct product);
}

