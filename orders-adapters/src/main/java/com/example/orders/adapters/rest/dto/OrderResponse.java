package com.example.orders.adapters.rest.dto;

import java.math.BigDecimal;

public record OrderResponse(
        String id,
        String productId,
        BigDecimal quantity,
        String status,
        String reservationStatus
) {}

