package com.example.orders.service;

import com.example.orders.event.OrderCreatedEvent;
import com.example.orders.event.OrderEventPublisher;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;

    public CreateOrderUseCase(OrderRepository repository,OrderEventPublisher eventPublisher) {
        this.repository = repository;
        this.eventPublisher=eventPublisher;
    }

    public Order execute(String productId, BigDecimal quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("productId is required");
        }
        if (quantity == null || quantity.signum() <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        Order order = new Order(UUID.randomUUID().toString(), productId, quantity);
        repository.save(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return order;
    }
}

