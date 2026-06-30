package com.example.shared;

import java.math.BigDecimal;

public record StockReservedEvent(String orderId, String productId, BigDecimal quantity) {}
