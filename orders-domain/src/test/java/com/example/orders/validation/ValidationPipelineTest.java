package com.example.orders.validation;

import com.example.orders.model.CustomerType;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class ValidationPipelineTest {

    private final ValidationPipeline pipeline = new ValidationPipeline();

    @Test
    void shouldFailWhenB2BCustomerUsesCOD() {
        OrderValidationContext context = new OrderValidationContext(
            "ORD-1",
            CustomerType.B2B,
            OrderValidationContext.PaymentMethod.COD,
            new BigDecimal("100.00"),
            "127.0.0.1", "US", "US", 0,
            "product-1", new BigDecimal("2"), new BigDecimal("5.0"), "123 Main St"
        );

        ValidationResult result = pipeline.execute(context);
        assertFalse(result.isValid());
        assertEquals("COD (Cash on Delivery) is only available for B2C customers.", result.errors().get(0));
    }

    @Test
    void shouldSucceedForValidStandardOrder() {
        OrderValidationContext context = new OrderValidationContext(
            "ORD-2",
            CustomerType.B2C,
            OrderValidationContext.PaymentMethod.CARD,
            new BigDecimal("100.00"),
            "127.0.0.1", "US", "US", 0,
            "product-2", new BigDecimal("1"), new BigDecimal("1.0"), "456 Oak Ave"
        );

        assertTrue(pipeline.execute(context).isValid());
    }
}
