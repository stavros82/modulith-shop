package com.example.orders.validation;

import java.math.BigDecimal;
import java.util.Optional;

public class MaxWeightValidator implements OrderValidator {
    private static final double DEFAULT_MAX_WEIGHT = 30.0;

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        BigDecimal weight = context.weight();
        if (weight != null && weight.doubleValue() > DEFAULT_MAX_WEIGHT) {
            return Optional.of("Order cannot exceed " + DEFAULT_MAX_WEIGHT + " kg");
        }
        return Optional.empty();
    }
}
