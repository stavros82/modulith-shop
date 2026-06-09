package com.example.orders.pricing;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * B2B Pricing Strategy
 * - Wholesale price (10% reduction from standard)
 * - No VAT for intra-EU shipments
 * - Invoice fee added (€2.50 flat)
 * - Contract shipping rates
 */
public class B2bPricingStrategy implements PricingStrategy {

    private static final BigDecimal B2B_WHOLESALE_DISCOUNT = new BigDecimal("0.10"); // 10% off
    private static final BigDecimal INVOICE_FEE = new BigDecimal("2.50");
    private static final BigDecimal EU_TAX_RATE = BigDecimal.ZERO; // 0% VAT for intra-EU

    @Override
    public PricingResult calculate(PricingContext context) {
        BigDecimal basePrice = context.getBasePrice();

        // Apply wholesale discount (10%)
        BigDecimal discount = basePrice.multiply(B2B_WHOLESALE_DISCOUNT)
                .setScale(2, RoundingMode.HALF_UP);

        BigDecimal priceAfterDiscount = basePrice.subtract(discount);

        // No VAT for intra-EU (assuming EU regions like "DE", "FR", "IT", etc.)
        BigDecimal tax = BigDecimal.ZERO;
        if (!isIntraEu(context.getShippingRegion())) {
            tax = priceAfterDiscount.multiply(context.getTaxRate())
                    .setScale(2, RoundingMode.HALF_UP);
        }

        // Contract shipping (typically lower than standard)
        // For simplicity, using 50% of standard shipping cost
        BigDecimal shipping = context.getStandardShippingCost()
                .multiply(new BigDecimal("0.50"))
                .setScale(2, RoundingMode.HALF_UP);

        // Add invoice fee
        BigDecimal invoiceFee = INVOICE_FEE;

        // Calculate final price
        BigDecimal finalPrice = priceAfterDiscount.add(tax).add(shipping).add(invoiceFee);

        return new PricingResult(
                basePrice,
                discount,
                B2B_WHOLESALE_DISCOUNT,
                tax,
                shipping,
                invoiceFee,
                finalPrice,
                "B2B"
        );
    }

    private boolean isIntraEu(String region) {
        // Simple check for EU country codes
        if (region == null) return false;
        String[] euRegions = {"AT", "BE", "BG", "HR", "CY", "CZ", "DK", "DE", "EE", "ES",
                             "FI", "FR", "GR", "HU", "IE", "IT", "LV", "LT", "LU", "MT",
                             "NL", "PL", "PT", "RO", "SK", "SI", "SE"};
        for (String eu : euRegions) {
            if (region.equalsIgnoreCase(eu)) {
                return true;
            }
        }
        return false;
    }
}

