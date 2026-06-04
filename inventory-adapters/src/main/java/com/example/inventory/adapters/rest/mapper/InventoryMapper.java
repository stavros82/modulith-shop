package com.example.inventory.adapters.rest.mapper;

import com.example.inventory.adapters.rest.dto.InventoryProductResponse;
import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;

import java.math.BigDecimal;

public class InventoryMapper {
   private InventoryMapper() {
      /* This utility class should not be instantiated */
   }

   public static InventoryProductResponse toResponse(InventoryProduct p) {
        if (p == null) {
            return new InventoryProductResponse(null, null, null, null, null, null);
        }

        BigDecimal available = p.availableQuantity();
        ProductSafetyStatus status = p.safetyStatus();

        return new InventoryProductResponse(
                p.productId(),
                status != null ? status.name() : null,
                p.stockQuantity(),
                p.reservedQuantity(),
                p.quarantinedQuantity(),
                available
        );
    }
}
