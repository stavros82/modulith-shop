package com.example.orders.validation;

import java.util.List;

public record ValidationResult(
    boolean isValid,
    List<String> errors
) {
    public static ValidationResult success() { return new ValidationResult(true, List.of()); }
    public static ValidationResult failure(String error) { return new ValidationResult(false, List.of(error)); }
}