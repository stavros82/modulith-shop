package com.example.orders.validation;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;

public class NoAlcoholWeekendsValidator implements OrderValidator {

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek day = now.getDayOfWeek();
        boolean isWeekend = day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY;

        if (!isWeekend) {
            return Optional.empty();
        }

        if (isAlcoholProduct(context.productId())) {
            return Optional.of("Alcohol products cannot be ordered on weekends");
        }

        return Optional.empty();
    }

    private boolean isAlcoholProduct(String productId) {
        if (productId == null) {
            return false;
        }
        String lower = productId.toLowerCase();
        return lower.contains("alcohol") || lower.contains("wine") || lower.contains("beer");
    }
}
