package com.example.orders.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Standard Pricing Strategy
 * - Base price
 * - Tax applied
 * - Standard shipping
 * - Bulk discount if quantity >= 10
 */
public class StandardPricingStrategy implements PricingStrategy {

    @Override
    public PricingResult calculate(PricingContext context) {
        BigDecimal basePrice = context.getBasePrice();

        // Calculate bulk discount (10% off if qty >= 10)
        BigDecimal discount = BigDecimal.ZERO;
        BigDecimal discountPercentage = BigDecimal.ZERO;

        if (context.getQuantity().compareTo(BigDecimal.TEN) >= 0) {
            discountPercentage = new BigDecimal("0.10"); // 10%
            discount = basePrice.multiply(discountPercentage);
        }

        BigDecimal priceAfterDiscount = basePrice.subtract(discount);

        // Calculate tax
        BigDecimal tax = priceAfterDiscount.multiply(context.getTaxRate())
                .setScale(2, RoundingMode.HALF_UP);

        // Add standard shipping
        BigDecimal shipping = context.getStandardShippingCost();

        // Calculate final price
        BigDecimal finalPrice = priceAfterDiscount.add(tax).add(shipping);

        return new PricingResult(
                basePrice,
                discount,
                discountPercentage,
                tax,
                shipping,
                BigDecimal.ZERO, // no invoice fee for B2C
                finalPrice,
                "STANDARD"
        );
    }
}

