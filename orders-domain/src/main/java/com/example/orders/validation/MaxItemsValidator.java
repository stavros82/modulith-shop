package com.example.orders.validation;

import java.math.BigDecimal;
import java.util.Optional;

public class MaxItemsValidator implements OrderValidator {
    private static final int DEFAULT_MAX_ITEMS = 50;

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        BigDecimal quantity = context.quantity();
        if (quantity != null && quantity.intValue() > DEFAULT_MAX_ITEMS) {
            return Optional.of("Order cannot exceed " + DEFAULT_MAX_ITEMS + " items");
        }
        return Optional.empty();
    }
}
