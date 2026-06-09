package com.example.orders.pricing;

import java.math.BigDecimal;

/**
 * Pricing calculation result with detailed breakdown.
 *
 * @param basePrice current order subtotal (quantity × unit price)
 * @param discount amount discounted (absolute value)
 * @param discountPercentage discount as percentage
 * @param tax calculated tax on (basePrice - discount)
 * @param shipping shipping cost (may be 0 for free shipping)
 * @param invoiceFee additional B2B invoice fee (may be 0)
 * @param finalPrice total amount customer pays
 * @param strategyUsed name of the pricing strategy applied
 */
public record PricingResult(
        BigDecimal basePrice,
        BigDecimal discount,
        BigDecimal discountPercentage,
        BigDecimal tax,
        BigDecimal shipping,
        BigDecimal invoiceFee,
        BigDecimal finalPrice,
        String strategyUsed
) {
    public PricingResult {
        if (basePrice == null) basePrice = BigDecimal.ZERO;
        if (discount == null) discount = BigDecimal.ZERO;
        if (discountPercentage == null) discountPercentage = BigDecimal.ZERO;
        if (tax == null) tax = BigDecimal.ZERO;
        if (shipping == null) shipping = BigDecimal.ZERO;
        if (invoiceFee == null) invoiceFee = BigDecimal.ZERO;
        if (finalPrice == null) finalPrice = BigDecimal.ZERO;
        if (strategyUsed == null) strategyUsed = "UNKNOWN";
    }
}

