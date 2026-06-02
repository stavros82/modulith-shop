package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

public class MarkOrderReservedUseCase {

    private final OrderRepository repository;

    public MarkOrderReservedUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public Order execute(String orderId) {
        Order order = repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));

        order.markReserved();
        return repository.save(order);
    }
}

