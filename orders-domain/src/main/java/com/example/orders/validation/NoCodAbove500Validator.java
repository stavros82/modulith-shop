package com.example.orders.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import com.example.orders.model.Order;
import java.math.BigDecimal;

public class NoCodAbove500Validator implements ConstraintValidator<NoCodAbove500, Object> {
    private double threshold;

    @Override
    public void initialize(NoCodAbove500 annotation) {
        this.threshold = annotation.threshold();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (!(value instanceof Order)) {
            return true;
        }
        
        Order order = (Order) value;
        
        // Check if payment method is COD (Cash on Delivery)
        String paymentMethod = order.paymentMethod() != null ? order.paymentMethod() : "";
        boolean isCod = paymentMethod.equalsIgnoreCase("COD") || 
                        paymentMethod.equalsIgnoreCase("cash on delivery");
        
        if (!isCod) {
            return true; // Not COD, allow order
        }
        
        // Check if order total exceeds threshold (€500)
        BigDecimal orderTotal = order.orderTotal() != null ? order.orderTotal() : BigDecimal.ZERO;
        
        if (orderTotal.doubleValue() > threshold) {
            addConstraintViolation(context);
            return false;
        }
        
        return true;
    }

    private void addConstraintViolation(ConstraintValidatorContext context) {
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(
            String.format("Cash on Delivery not allowed for orders exceeding €%.2f", threshold))
               .addConstraintViolation();
    }
}

