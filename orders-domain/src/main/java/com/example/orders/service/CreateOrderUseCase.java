package com.example.orders.service;

import com.example.orders.event.OrderCreatedEvent;
import com.example.orders.event.OrderEventPublisher;
import com.example.orders.model.Order;
import com.example.orders.repository.OrderRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import com.example.orders.exception.BusinessValidationException;
import java.util.List;
import java.util.ArrayList;
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


        Order order = new Order(UUID.randomUUID().toString(), productId, quantity,
                                shippingAddress, paymentMethod, weight, orderTotal);

        // Validate Order domain model against business rules
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (!violations.isEmpty()) {
            List<String> msgs = new ArrayList<>();
            violations.forEach(v -> msgs.add(v.getMessage()));
            String violationMessages = String.join(", ", msgs);
            throw new BusinessValidationException("Order validation failed: " + violationMessages, msgs);
        }

        repository.save(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return order;
    }
}

