package com.example.orders.pricing;

import java.math.BigDecimal;

/**
 * Strategy interface for different pricing algorithms.
 * Implementations calculate order price based on different business rules:
 * - Standard: Base price + tax + shipping + bulk discount
 * - VIP: 10-20% discount + free shipping
 * - B2B: Wholesale price + no VAT (intra-EU) + invoice fee
 * - Campaign: Campaign/product/loyalty discounts
 * - Black Friday: 30-70% discount + free shipping
 */
public interface PricingStrategy {
    /**
     * Calculate the final price and breakdown for an order.
     *
     * @param context the pricing context with order details
     * @return pricing result with breakdown
     */
    PricingResult calculate(PricingContext context);
}

