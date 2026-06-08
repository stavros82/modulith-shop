package com.example.orders.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MaxItemsValidator implements ConstraintValidator<MaxItems, BigDecimal> {
    private int maxItems;

    @Override
    public void initialize(MaxItems annotation) {
        this.maxItems = annotation.value();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.intValue() <= maxItems;
    }
}

