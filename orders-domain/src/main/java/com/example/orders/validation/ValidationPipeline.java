package com.example.orders.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ValidationPipeline {
    private final List<OrderValidator> validators = new ArrayList<>();

    public ValidationPipeline() {
        validators.add(new PaymentMethodValidator());
        validators.add(new NoCodAbove500Validator());
        validators.add(new MaxItemsValidator());
        validators.add(new MaxWeightValidator());
        validators.add(new NoAlcoholWeekendsValidator());
        validators.add(new NoElectronicsToPoBoxValidator());
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
