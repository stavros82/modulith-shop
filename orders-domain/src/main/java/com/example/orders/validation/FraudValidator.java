package com.example.orders.validation;

import java.math.BigDecimal;
import java.util.Optional;

public class FraudValidator implements OrderValidator {
    private static final BigDecimal HIGH_AMOUNT_LIMIT = new BigDecimal("10000.00");

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        // 8.4 Fraud Validation
        if (context.totalAmount().compareTo(HIGH_AMOUNT_LIMIT) > 0) {
            return Optional.of("Order amount exceeds the automatic fraud threshold.");
        }

        if (context.previousFailedPayments() > 3) {
            return Optional.of("Order blocked due to excessive failed payment attempts.");
        }

        if (!context.billingCountry().equals(context.shippingCountry())) {
            return Optional.of("Fraud Alert: Billing and Shipping countries must match.");
        }

        return Optional.empty();
    }
}