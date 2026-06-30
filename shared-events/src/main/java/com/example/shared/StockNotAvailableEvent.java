package com.example.shared;

import java.math.BigDecimal;

public record StockNotAvailableEvent(
        String orderId,
        String productId,
        BigDecimal requested,
        BigDecimal available
) {}
