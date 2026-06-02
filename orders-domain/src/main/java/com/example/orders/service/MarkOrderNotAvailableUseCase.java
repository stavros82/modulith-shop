package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

public class MarkOrderNotAvailableUseCase {

    private final OrderRepository repository;

    public MarkOrderNotAvailableUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public Order execute(String orderId) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        order.markNotAvailable();
        return repository.save(order);
    }
}

