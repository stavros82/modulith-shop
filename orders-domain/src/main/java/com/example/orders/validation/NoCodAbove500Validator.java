package com.example.orders.validation;

import java.math.BigDecimal;
import java.util.Optional;

public class NoCodAbove500Validator implements OrderValidator {
    private static final BigDecimal COD_THRESHOLD = new BigDecimal("500.0");

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        if (context.paymentMethod() != OrderValidationContext.PaymentMethod.COD) {
            return Optional.empty();
        }

        BigDecimal total = context.totalAmount() != null ? context.totalAmount() : BigDecimal.ZERO;
        if (total.compareTo(COD_THRESHOLD) > 0) {
            return Optional.of(
                String.format("Cash on Delivery not allowed for orders exceeding €%.2f", COD_THRESHOLD)
            );
        }

        return Optional.empty();
    }
}
