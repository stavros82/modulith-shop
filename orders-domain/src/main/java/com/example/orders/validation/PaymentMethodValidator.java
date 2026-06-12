package com.example.orders.validation;


import com.example.orders.model.CustomerType;

import java.util.Optional;

public class PaymentMethodValidator implements OrderValidator {
    @Override
    public Optional<String> validate(OrderValidationContext context) {
        // 8.3 Payment Method Validation
        if (context.paymentMethod() == OrderValidationContext.PaymentMethod.COD 
            && context.customerType() == CustomerType.B2B) {
            return Optional.of("COD (Cash on Delivery) is only available for B2C customers.");
        }

        if (context.paymentMethod() == OrderValidationContext.PaymentMethod.INVOICE 
            && context.customerType() != CustomerType.B2B) {
            return Optional.of("Invoice payment is strictly reserved for B2B customers.");
        }

        return Optional.empty();
    }
}