package com.example.orders.pricing;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class StandardPricingStrategyTest {

    private StandardPricingStrategy strategy;

    @BeforeEach
    void setUp() {
        strategy = new StandardPricingStrategy();
    }

    @Test
    @DisplayName("Should calculate total price and tax correctly for standard order")
    void shouldCalculateStandardPricing() {
        // Arrange
        BigDecimal unitPrice = new BigDecimal("100.00");
        BigDecimal quantity = new BigDecimal("2");
        BigDecimal taxRate = new BigDecimal("0.10"); // 10%
        PricingContext context = createPricingContext(unitPrice, quantity, taxRate);

        // Act
        PricingResult result = strategy.calculate(context);

        // Assert
        assertNotNull(result);
        // Logic: (200.00 base) + (20.00 tax) + (5.00 shipping) = 225.00
        assertBigDecimalEquals(new BigDecimal("225.00"), result.finalPrice(), "Final price should be base + tax + shipping");
        assertBigDecimalEquals(new BigDecimal("20.00"), result.tax(), "Tax should be 10% of base");
    }

    @Test
    @DisplayName("Should apply 10% bulk discount when quantity is 10 or more")
    void shouldApplyBulkDiscount() {
        // Arrange
        BigDecimal unitPrice = new BigDecimal("100.00");
        BigDecimal quantity = new BigDecimal("10");
        BigDecimal taxRate = new BigDecimal("0.10");
        PricingContext context = createPricingContext(unitPrice, quantity, taxRate);

        // Act
        PricingResult result = strategy.calculate(context);

        // Assert
        // Base: 1000.00
        // Discount: 100.00 (10%)
        // Price after discount: 900.00
        // Tax: 90.00 (10% of 900)
        // Shipping: 5.00
        // Total: 995.00
        assertBigDecimalEquals(new BigDecimal("100.00"), result.discount(), "Should apply 10% bulk discount");
        assertBigDecimalEquals(new BigDecimal("995.00"), result.finalPrice(), "Final price should reflect bulk discount");
    }

    @Test
    @DisplayName("Should return zero for an empty list of items")
    void shouldHandleEmptyCart() {
        // Arrange
        PricingContext context = createPricingContext(BigDecimal.ZERO, BigDecimal.ZERO, new BigDecimal("0.10"));

        // Act
        PricingResult result = strategy.calculate(context);

        // Assert
        assertBigDecimalEquals(new BigDecimal("5.00"), result.finalPrice(), "Only shipping should remain for empty cart");
    }

    /**
     * Helper method to create a PricingContext with sensible defaults.
     */
    private PricingContext createPricingContext(BigDecimal unitPrice, BigDecimal quantity, BigDecimal taxRate) {
        BigDecimal basePrice = unitPrice.multiply(quantity);
        return new PricingContext(
                basePrice,
                quantity,
                unitPrice,
                PricingContext.CustomerType.B2C,
                false, // isVip
                null,  // campaign
                "DEFAULT_REGION",
                false, // isBlackFriday
                taxRate,
                new BigDecimal("5.00") // standardShippingCost
        );
    }

    private void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual, String message) {
        assertEquals(0, expected.compareTo(actual), 
            String.format("%s - Expected: %s, Actual: %s", message, expected, actual));
    }
}