package com.example.orders.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoCodAbove500Validator.class)
@Documented
public @interface NoCodAbove500 {
    String message() default "Cash on Delivery not allowed for orders exceeding €500";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    
    double threshold() default 500.0;
}

