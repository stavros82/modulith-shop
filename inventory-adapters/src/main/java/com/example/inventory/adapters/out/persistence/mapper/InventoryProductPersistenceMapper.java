package com.example.inventory.adapters.out.persistence.mapper;

import com.example.inventory.adapters.out.persistence.entity.InventoryProductJpaEntity;
import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;

public class InventoryProductPersistenceMapper {

    private InventoryProductPersistenceMapper() {}

    public static InventoryProduct toDomain(InventoryProductJpaEntity e) {
        if (e == null) {
            return null;
        }

        ProductSafetyStatus status = e.getSafetyStatus() != null
                ? ProductSafetyStatus.valueOf(e.getSafetyStatus())
                : ProductSafetyStatus.ACTIVE;

        return new InventoryProduct(
                e.getProductId(),
                e.getProductName(),
                status,
                e.getStockQuantity(),
                e.getReservedQuantity(),
                e.getQuarantinedQuantity()
        );
    }

    public static InventoryProductJpaEntity toEntity(InventoryProduct product) {
        if (product == null) {
            return null;
        }

        InventoryProductJpaEntity e = new InventoryProductJpaEntity();
        e.setProductId(product.productId());
        e.setProductName(product.productName());
        e.setSafetyStatus(product.safetyStatus().name());
        e.setStockQuantity(product.stockQuantity());
        e.setReservedQuantity(product.reservedQuantity());
        e.setQuarantinedQuantity(product.quarantinedQuantity());
        return e;
    }
}

