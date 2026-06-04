package com.example.orders.adapters.in.rest.dto;

import java.math.BigDecimal;

public record CreateOrderRequest(
        String productId,
        BigDecimal quantity
) {}

