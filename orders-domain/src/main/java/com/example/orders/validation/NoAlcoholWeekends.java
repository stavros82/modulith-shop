package com.example.orders.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoAlcoholWeekendsValidator.class)
@Documented
public @interface NoAlcoholWeekends {
    String message() default "Alcohol products cannot be ordered on weekends";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

