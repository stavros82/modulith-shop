package com.example.inventory.adapters.in.rest.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.service.GetInventoryProductUseCase;
import com.example.inventory.service.ReplenishStockUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class InventoryManagementService {

    private final GetInventoryProductUseCase getInventoryProductUseCase;
    private final ReplenishStockUseCase replenishStockUseCase;

    public InventoryManagementService(
            GetInventoryProductUseCase getInventoryProductUseCase,
            ReplenishStockUseCase replenishStockUseCase){
        this.getInventoryProductUseCase = getInventoryProductUseCase;
        this.replenishStockUseCase = replenishStockUseCase;

    }

    @Transactional
    public InventoryProduct replenishStock(String productId, BigDecimal quantity) {
        return replenishStockUseCase.execute(productId, quantity);
    }

    @Transactional
    public InventoryProduct execute(String productId) {
        return getInventoryProductUseCase.execute(productId);
    }
}
