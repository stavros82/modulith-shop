package com.example.orders.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.math.BigDecimal;

public class MaxWeightValidator implements ConstraintValidator<MaxWeight, BigDecimal> {
    private double maxWeight;

    @Override
    public void initialize(MaxWeight annotation) {
        this.maxWeight = annotation.value();
    }

    @Override
    public boolean isValid(BigDecimal value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.doubleValue() <= maxWeight;
    }
}

