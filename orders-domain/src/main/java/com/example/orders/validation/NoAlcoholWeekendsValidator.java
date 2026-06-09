package com.example.orders.validation;

import com.example.orders.service.CreateOrderCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDateTime;
import java.time.DayOfWeek;

public class NoAlcoholWeekendsValidator implements ConstraintValidator<NoAlcoholWeekends, Object> {

    @Override
    public void initialize(NoAlcoholWeekends annotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!(value instanceof CreateOrderCommand order)) {
            return true;
        }

        // Check if today is weekend (Saturday or Sunday)
        LocalDateTime now = LocalDateTime.now();
        DayOfWeek dayOfWeek = now.getDayOfWeek();
        boolean isWeekend = dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;

        if (!isWeekend) {
            return true; // Not weekends, allow all orders
        }

        // Check if order contains alcohol (indicated by product category or flag)
        // This is a simplified check; in production, you'd query product metadata
        boolean hasAlcohol = isAlcoholProduct(order.productId());

        if (hasAlcohol) {
            addConstraintViolation(context);
            return false;
        }

        return true;
    }

    private boolean isAlcoholProduct(String productId) {
        // In production, query catalog via event or maintain local cache
        // For now, simple string check (e.g., product names containing "wine", "beer")
        // You can expand this to use Catalog events or a local product cache
        return productId != null && (productId.toLowerCase().contains("alcohol") ||
                                      productId.toLowerCase().contains("wine") ||
                                      productId.toLowerCase().contains("beer"));
    }

    private void addConstraintViolation(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Alcohol products cannot be ordered on weekends")
               .addConstraintViolation();
    }
}

