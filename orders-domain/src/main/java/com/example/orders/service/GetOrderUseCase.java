package com.example.orders.service;

import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

public class GetOrderUseCase {

    private final OrderRepository repository;

    public GetOrderUseCase(OrderRepository repository) {
        this.repository = repository;
    }

    public Order execute(String orderId) {
        return repository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found: " + orderId));
    }
}

