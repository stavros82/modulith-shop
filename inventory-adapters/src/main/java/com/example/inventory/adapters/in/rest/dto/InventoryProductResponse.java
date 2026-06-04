package com.example.inventory.adapters.in.rest.dto;

import java.math.BigDecimal;

public record InventoryProductResponse(
        String productId,
        String safetyStatus,
        BigDecimal stockQuantity,
        BigDecimal reservedQuantity,
        BigDecimal quarantinedQuantity,
        BigDecimal availableQuantity
) {}

