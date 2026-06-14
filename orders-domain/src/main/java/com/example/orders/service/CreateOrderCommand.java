package com.example.orders.service;

import com.example.orders.validation.NoAlcoholWeekends;

import com.example.orders.validation.NoElectronicsToPoBox;

import java.math.BigDecimal;

@NoAlcoholWeekends
@NoElectronicsToPoBox

public record CreateOrderCommand(
    String productId,
    BigDecimal quantity,
    String shippingAddress,
    String paymentMethod,
    BigDecimal weight,
    // Add all raw fields needed for pricing here
    BigDecimal unitPrice,
    String customerType,
    boolean isVip,
    String shippingRegion,
    String requestIp,
    String billingCountry,
    String shippingCountry,
    int previousFailedPayments
) {}
