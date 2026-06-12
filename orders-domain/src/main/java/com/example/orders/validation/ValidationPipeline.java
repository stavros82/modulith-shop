package com.example.orders.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidationPipeline {
    private final List<OrderValidator> validators = new ArrayList<>();

    public ValidationPipeline() {
        // Initialize with steps from 8.3 and 8.4
        validators.add(new PaymentMethodValidator());
        validators.add(new FraudValidator());
    }

    public ValidationResult execute(OrderValidationContext context) {
        for (OrderValidator validator : validators) {
            Optional<String> error = validator.validate(context);
            if (error.isPresent()) {
                return ValidationResult.failure(error.get());
            }
        }
        return ValidationResult.success();
    }
}