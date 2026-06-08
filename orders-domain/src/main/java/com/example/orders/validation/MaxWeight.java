package com.example.orders.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MaxWeightValidator.class)
@Documented
public @interface MaxWeight {
    String message() default "Order cannot exceed {value} kg";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    double value() default 30.0;
}

