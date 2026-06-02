package com.example.orders.adapters.rest.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String productId,
        BigDecimal quantity
) {}

