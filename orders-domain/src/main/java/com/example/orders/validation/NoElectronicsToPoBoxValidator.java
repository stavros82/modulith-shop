package com.example.orders.validation;

import java.util.Optional;

public class NoElectronicsToPoBoxValidator implements OrderValidator {

    @Override
    public Optional<String> validate(OrderValidationContext context) {
        if (!isPoBoxAddress(context.shippingAddress())) {
            return Optional.empty();
        }

        if (isElectronicProduct(context.productId())) {
            return Optional.of("Electronics cannot be shipped to PO box addresses");
        }

        return Optional.empty();
    }

    private boolean isPoBoxAddress(String address) {
        if (address == null) {
            return false;
        }
        String lower = address.toLowerCase();
        return lower.contains("p.o. box") || lower.contains("po box")
            || lower.contains("p.o box") || lower.contains("pobox");
    }

    private boolean isElectronicProduct(String productId) {
        if (productId == null) {
            return false;
        }
        String lower = productId.toLowerCase();
        return lower.contains("electronic") || lower.contains("computer")
            || lower.contains("phone") || lower.contains("laptop");
    }
}
