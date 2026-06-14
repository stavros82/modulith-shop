package com.example.orders.validation;

import java.util.Optional;

public interface OrderValidator {
    Optional<String> validate(OrderValidationContext context);
}