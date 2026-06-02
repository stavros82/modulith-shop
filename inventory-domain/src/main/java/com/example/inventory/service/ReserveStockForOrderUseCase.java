package com.example.inventory.service;

import com.example.inventory.model.InventoryProduct;
import com.example.inventory.model.ProductSafetyStatus;
import com.example.inventory.repository.InventoryProductRepository;

import java.math.BigDecimal;

public class ReserveStockForOrderUseCase {

    private final InventoryProductRepository repository;

    public ReserveStockForOrderUseCase(InventoryProductRepository repository) {
        this.repository = repository;
    }

    public ReservationResult execute(String orderId, String productId, BigDecimal quantity) {
        InventoryProduct product = repository.findByProductId(productId)
                .orElseGet(() -> repository.save(new InventoryProduct(productId, null, ProductSafetyStatus.ACTIVE)));

        BigDecimal available = product.availableQuantity();
        boolean reserved = product.tryReserve(quantity);
        if (!reserved) {
            return new NotAvailable(orderId, productId, quantity, available);
        }

        repository.save(product);
        return new Reserved(orderId, productId, quantity);
    }

    public sealed interface ReservationResult permits Reserved, NotAvailable {
        String orderId();
        String productId();
    }

    public record Reserved(String orderId, String productId, BigDecimal quantity) implements ReservationResult {}

    public record NotAvailable(String orderId, String productId, BigDecimal requested, BigDecimal available) implements ReservationResult {}

    public static Reserved reserved(String orderId, String productId, BigDecimal quantity) {
        return new Reserved(orderId, productId, quantity);
    }

    public static NotAvailable notAvailable(String orderId, String productId, BigDecimal requested, BigDecimal available) {
        return new NotAvailable(orderId, productId, requested, available);
    }
}

