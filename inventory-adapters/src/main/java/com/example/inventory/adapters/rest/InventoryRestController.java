package com.example.inventory.adapters.rest;

import com.example.inventory.adapters.rest.dto.InventoryProductResponse;
import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.service.GetInventoryProductUseCase;
import com.example.inventory.service.ReplenishStockUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/inventory")
public class InventoryRestController {

    private final GetInventoryProductUseCase getInventoryProductUseCase;
    private final ReplenishStockUseCase replenishStockUseCase;

    public InventoryRestController(
            GetInventoryProductUseCase getInventoryProductUseCase,
            ReplenishStockUseCase replenishStockUseCase
    ) {
        this.getInventoryProductUseCase = getInventoryProductUseCase;
        this.replenishStockUseCase = replenishStockUseCase;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryProductResponse> getProduct(@PathVariable("productId") String productId) {
        InventoryProduct product = getInventoryProductUseCase.execute(productId);
        return ResponseEntity.ok(toResponse(product));
    }

    @PostMapping("/products/{productId}/replenish")
    public ResponseEntity<InventoryProductResponse> replenish(
            @PathVariable("productId") String productId,
            @RequestParam("quantity") BigDecimal quantity
    ) {
        InventoryProduct product = replenishStockUseCase.execute(productId, quantity);
        return ResponseEntity.ok(toResponse(product));
    }

    private static InventoryProductResponse toResponse(InventoryProduct p) {
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

