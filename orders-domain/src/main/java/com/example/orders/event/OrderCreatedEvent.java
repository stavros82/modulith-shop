package com.example.orders.event;

import java.math.BigDecimal;

public record OrderCreatedEvent(String orderId, String productId, BigDecimal quantity) {

}

