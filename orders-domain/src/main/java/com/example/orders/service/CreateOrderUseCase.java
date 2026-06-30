package com.example.orders.service;

import com.example.shared.OrderCreatedEvent;
import com.example.orders.event.OrderEventPublisher;
import com.example.orders.model.CustomerType;
import com.example.orders.model.Order;
import com.example.orders.pricing.*;
import com.example.orders.validation.*;
import com.example.orders.repository.OrderRepository;
import com.example.orders.exception.BusinessValidationException;

import java.math.BigDecimal;
import java.util.UUID;

public class CreateOrderUseCase {

    private final OrderRepository repository;
    private final OrderEventPublisher eventPublisher;
    private final PricingService pricingService;
    private final ValidationPipeline validationPipeline;

    public CreateOrderUseCase(OrderRepository repository, OrderEventPublisher eventPublisher,
                              PricingService pricingService,
                              ValidationPipeline validationPipeline) {
        this.repository = repository;
        this.eventPublisher = eventPublisher;
        this.pricingService = pricingService;
        this.validationPipeline = validationPipeline;
    }

    public Order execute(CreateOrderCommand command) {
        // 1. Pricing Logic
        BigDecimal orderTotal = pricingService.calculateOrderTotal(command);

        // 2. Business & Fraud Validation (BRD 8.3 & 8.4)
        OrderValidationContext validationContext = mapToValidationContext(command, orderTotal);
        ValidationResult validationResult = validationPipeline.execute(validationContext);

        if (!validationResult.isValid()) {
            throw new BusinessValidationException("Order rejected by validation rules", validationResult.errors());
        }

        // 3. Create Domain Model
        Order order = new Order(UUID.randomUUID().toString(), command.productId(), command.quantity(),
                command.shippingAddress(), command.paymentMethod(), command.weight(), orderTotal);

        // 4. Save and Publish
        repository.save(order);
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return order;
    }

    private OrderValidationContext mapToValidationContext(CreateOrderCommand command, BigDecimal total) {
        return new OrderValidationContext(
                null,
                CustomerType.valueOf(command.customerType()),
                OrderValidationContext.PaymentMethod.valueOf(command.paymentMethod().toUpperCase()),
                total,
                command.requestIp(),
                command.billingCountry(),
                command.shippingCountry(),
                command.previousFailedPayments(),
                command.productId(),
                command.quantity(),
                command.weight(),
                command.shippingAddress()
        );
    }
}
