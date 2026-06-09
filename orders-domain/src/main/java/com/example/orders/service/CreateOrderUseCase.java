package com.example.orders.service;

import com.example.orders.event.OrderCreatedEvent;
import com.example.orders.event.OrderEventPublisher;
import com.example.orders.model.Order;
import com.example.orders.pricing.*;
import com.example.orders.repository.OrderRepository;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import com.example.orders.exception.BusinessValidationException;
import java.util.List;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.util.Set;
import java.util.UUID;


public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;
    private final Validator validator;
    private final PricingService pricingService;

    public CreateOrderUseCase(OrderRepository repository, OrderEventPublisher eventPublisher, Validator validator, PricingService pricingService) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.validator = validator;
        this.pricingService = pricingService;
    }

    public Order execute(CreateOrderCommand command) {
        // 1. Structural Validation (Fail Fast)
        validate(command);

        // 2. Get price (Abstraction layer)

        BigDecimal orderTotal = pricingService.calculateOrderTotal(command);

        // 3. Create Domain Model
        Order order = new Order(UUID.randomUUID().toString(), command.productId(), command.quantity(),
                command.shippingAddress(), command.paymentMethod(), command.weight(), orderTotal);

        validate(order);
        // 5. Save and Publish
        repository.save(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return order;
    }

     private void validate(Object object) {
        // Validate CreateOrderCommand against structural rules (e.g. non-null, positive quantity)
        Set<ConstraintViolation<Object>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            List<String> msgs = new ArrayList<>();
            violations.forEach(v -> msgs.add(v.getMessage()));
            String violationMessages = String.join(", ", msgs);
            throw new BusinessValidationException("CreateOrderCommand validation failed: " + violationMessages, msgs);
        }
    }

}

