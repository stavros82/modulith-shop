package com.example.orders.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * VIP Pricing Strategy
 * - 10–20% discount (using 15% as standard)
 * - Free shipping
 * - Priority handling
 * - No discount stacking
 */
public class VipPricingStrategy implements PricingStrategy {

    private static final BigDecimal VIP_DISCOUNT_PERCENTAGE = new BigDecimal("0.15"); // 15%

    @Override
    public PricingResult calculate(PricingContext context) {
        BigDecimal basePrice = context.getBasePrice();

        // Apply VIP discount (15%)
        BigDecimal discount = basePrice.multiply(VIP_DISCOUNT_PERCENTAGE)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal priceAfterDiscount = basePrice.subtract(discount);

        // Calculate tax
        BigDecimal tax = priceAfterDiscount.multiply(context.getTaxRate())
                .setScale(2, RoundingMode.HALF_UP);

        // Free shipping for VIP
        BigDecimal shipping = BigDecimal.ZERO;

        // Calculate final price
        BigDecimal finalPrice = priceAfterDiscount.add(tax).add(shipping);

        return new PricingResult(
                basePrice,
                discount,
                VIP_DISCOUNT_PERCENTAGE,
                tax,
                shipping,
                BigDecimal.ZERO,
                finalPrice,
                "VIP"
        );
    }
}

