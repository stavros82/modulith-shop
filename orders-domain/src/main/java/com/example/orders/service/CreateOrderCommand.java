package com.example.orders.service;

import java.math.BigDecimal;

public record CreateOrderCommand(
    String productId,
    BigDecimal quantity,
    String shippingAddress,
    String paymentMethod,
    BigDecimal weight,
    BigDecimal unitPrice,
    String customerType,
    boolean isVip,
    String shippingRegion,
    String requestIp,
    String billingCountry,
    String shippingCountry,
    int previousFailedPayments
) {}
