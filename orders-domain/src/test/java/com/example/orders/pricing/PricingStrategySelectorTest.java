package com.example.orders.pricing;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class PricingStrategySelectorTest {

    private final PricingStrategySelector selector = new PricingStrategySelector();

    @Test
    void calculate_B2BCustomer_ShouldReturnB2BPricing() {
        // Arrange
        PricingContext context = createBaseContext();
        PricingContext b2bContext = new PricingContext(
                context.basePrice(), context.quantity(), context.unitPrice(),
                PricingContext.CustomerType.B2B, false, null, "DE", 
                false, new BigDecimal("0.19"), new BigDecimal("5.00")
        );

        // Act
        PricingResult result = selector.calculate(b2bContext);

        // Assert
        assertNotNull(result);
        assertEquals("B2B", result.strategyUsed());
    }

    @Test
    void calculate_VipCustomer_ShouldReturnVipPricing() {
        // Arrange
        PricingContext context = createBaseContext();
        PricingContext vipContext = new PricingContext(
                context.basePrice(), context.quantity(), context.unitPrice(),
                PricingContext.CustomerType.B2C, true, null, "DE", 
                false, new BigDecimal("0.19"), new BigDecimal("5.00")
        );

        // Act
        PricingResult result = selector.calculate(vipContext);

        // Assert
        assertNotNull(result);
        assertEquals("VIP", result.strategyUsed());
    }

    @Test
    void calculate_StandardCustomer_ShouldReturnStandardPricing() {
        // Arrange
        PricingContext context = createBaseContext();
        PricingContext standardContext = new PricingContext(
                context.basePrice(), context.quantity(), context.unitPrice(),
                PricingContext.CustomerType.B2C, false, null, "DE", 
                false, new BigDecimal("0.19"), new BigDecimal("5.00")
        );

        // Act
        PricingResult result = selector.calculate(standardContext);

        // Assert
        assertNotNull(result);
        assertEquals("STANDARD", result.strategyUsed());
    }

    private PricingContext createBaseContext() {
        return new PricingContext(
                new BigDecimal("100.00"),
                new BigDecimal("1"),
                new BigDecimal("100.00"),
                PricingContext.CustomerType.B2C,
                false,
                null,
                "DE",
                false,
                new BigDecimal("0.19"),
                new BigDecimal("5.00")
        );
    }
}
