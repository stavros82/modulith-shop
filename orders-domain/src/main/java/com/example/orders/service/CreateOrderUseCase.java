package com.example.orders.service;

import com.example.orders.event.OrderCreatedEvent;
import com.example.orders.event.OrderEventPublisher;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;
    private final Validator validator;

    public CreateOrderUseCase(OrderRepository repository, OrderEventPublisher eventPublisher, Validator validator) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.validator = validator;
    }

    public Order execute(String productId, BigDecimal quantity, String shippingAddress,
                         String paymentMethod, BigDecimal weight, BigDecimal orderTotal) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("productId is required");
        }
        if (quantity == null || quantity.signum() <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }

        Order order = new Order(UUID.randomUUID().toString(), productId, quantity,
                                shippingAddress, paymentMethod, weight, orderTotal);

        // Validate Order domain model against business rules
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (!violations.isEmpty()) {
            String violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new IllegalArgumentException("Order validation failed: " + violationMessages);
        }

        repository.save(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return order;
    }
}

