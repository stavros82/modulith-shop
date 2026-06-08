package com.example.orders.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoElectronicsToPoBoxValidator.class)
@Documented
public @interface NoElectronicsToPoBox {
    String message() default "Electronics cannot be shipped to PO box addresses";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

