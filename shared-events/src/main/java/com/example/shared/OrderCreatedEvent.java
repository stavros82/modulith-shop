package com.example.shared;

import java.math.BigDecimal;

public record OrderCreatedEvent(String orderId, String productId, BigDecimal quantity) {}
