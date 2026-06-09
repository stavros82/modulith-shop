package com.example.orders.validation;

import com.example.orders.service.CreateOrderCommand;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.orders.model.Order;

public class NoElectronicsToPoBoxValidator implements ConstraintValidator<NoElectronicsToPoBox, Object> {

    @Override
    public void initialize(NoElectronicsToPoBox annotation) {
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!(value instanceof CreateOrderCommand order)) {
            return true;
        }

        // Check if shipping address is a PO box
        String address = order.shippingAddress() != null ? order.shippingAddress() : "";
        boolean isPoBox = isPOBoxAddress(address);
        
        if (!isPoBox) {
            return true; // Not a PO box, allow all orders
        }
        
        // Check if order contains electronics
        boolean hasElectronics = isElectronicProduct(order.productId());
        
        if (hasElectronics) {
            addConstraintViolation(context);
            return false;
        }
        
        return true;
    }

    private boolean isPOBoxAddress(String address) {
        // Simple check for PO box patterns
        String lowerAddress = address.toLowerCase();
        return lowerAddress.contains("p.o. box") || 
               lowerAddress.contains("po box") || 
               lowerAddress.contains("p.o box") || 
               lowerAddress.contains("pobox");
    }

    private boolean isElectronicProduct(String productId) {
        // Check if product is electronics
        // In production, query catalog via event or maintain local cache
        return productId != null && (productId.toLowerCase().contains("electronic") || 
                                      productId.toLowerCase().contains("computer") || 
                                      productId.toLowerCase().contains("phone") || 
                                      productId.toLowerCase().contains("laptop"));
    }

    private void addConstraintViolation(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate("Electronics cannot be shipped to PO box addresses")
               .addConstraintViolation();
    }
}

