package com.example.inventory.adapters.in.rest;

import com.example.inventory.adapters.in.rest.dto.InventoryProductResponse;
import com.example.inventory.adapters.in.rest.service.InventoryManagementService;
import com.example.inventory.model.InventoryProduct;
import com.example.inventory.service.GetInventoryProductUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import static com.example.inventory.adapters.in.rest.mapper.InventoryMapper.toResponse;

@RestController
@RequestMapping("/inventory")
public class InventoryRestController {


    private final InventoryManagementService inventoryManagementService;

    public InventoryRestController(
            InventoryManagementService inventoryManagementService
    ) {

        this.inventoryManagementService = inventoryManagementService;
    }

    @GetMapping("/products/{productId}")
    public ResponseEntity<InventoryProductResponse> getProduct(@PathVariable("productId") String productId) {
        InventoryProduct product = inventoryManagementService.execute(productId);
        return ResponseEntity.ok(toResponse(product));
    }

    @PostMapping("/products/{productId}/replenish")
    public ResponseEntity<InventoryProductResponse> replenish(
            @PathVariable("productId") String productId,
            @RequestParam("quantity") BigDecimal quantity
    ) {
        InventoryProduct product = inventoryManagementService.replenishStock(productId, quantity);
        return ResponseEntity.ok(toResponse(product));
    }
}
