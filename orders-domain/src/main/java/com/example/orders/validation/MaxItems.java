package com.example.orders.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxItemsValidator.class)
@Documented
public @interface MaxItems {
    String message() default "Order cannot exceed {value} items";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    int value() default 50;
}

