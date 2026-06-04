package com.example.inventory.event;

import java.math.BigDecimal;

public record StockReservedEvent(String orderId, String productId, BigDecimal quantity) {

}

