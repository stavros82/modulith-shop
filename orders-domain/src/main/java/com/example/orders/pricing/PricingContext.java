package com.example.orders.pricing;

import com.example.orders.model.CustomerType;

import java.math.BigDecimal;


/**
 * Context object containing all information needed for pricing calculation.
 * Passed to pricing strategies to determine price.
 *
 * @param basePrice      subtotal before discounts
 * @param quantity       number of items
 * @param unitPrice      price per single unit
 * @param customerType   B2C or B2B
 * @param isVipCustomer  flag for VIP status
 * @param campaign       null if no campaign
 * @param shippingRegion for tax/shipping calculation
 * @param isBlackFridayPeriod flag for seasonal discount
 * @param taxRate        e.g., 0.19 for 19% VAT
 * @param standardShippingCost base shipping cost
 */
public record PricingContext(BigDecimal basePrice, BigDecimal quantity, BigDecimal unitPrice,
                             CustomerType customerType, boolean isVipCustomer,
                             PricingContext.CampaignInfo campaign, String shippingRegion,
                             boolean isBlackFridayPeriod, BigDecimal taxRate, BigDecimal standardShippingCost) {
    public PricingContext(BigDecimal basePrice, BigDecimal quantity, BigDecimal unitPrice,
                          CustomerType customerType, boolean isVipCustomer, CampaignInfo campaign,
                          String shippingRegion, boolean isBlackFridayPeriod,
                          BigDecimal taxRate, BigDecimal standardShippingCost) {
        this.basePrice = basePrice != null ? basePrice : BigDecimal.ZERO;
        this.quantity = quantity != null ? quantity : BigDecimal.ZERO;
        this.unitPrice = unitPrice != null ? unitPrice : BigDecimal.ZERO;
        this.customerType = customerType;
        this.isVipCustomer = isVipCustomer;
        this.campaign = campaign;
        this.shippingRegion = shippingRegion;
        this.isBlackFridayPeriod = isBlackFridayPeriod;
        this.taxRate = taxRate != null ? taxRate : BigDecimal.ZERO;
        this.standardShippingCost = standardShippingCost != null ? standardShippingCost : BigDecimal.ZERO;
    }


    /**
     * Campaign information for campaign pricing strategy
     */
    public static class CampaignInfo {
        private final String campaignName;
        private final BigDecimal campaignDiscount; // e.g., 0.10 for 10% off
        private final BigDecimal productDiscount;  // product-specific discount
        private final BigDecimal loyaltyDiscount;  // loyalty program discount
        private final boolean isBuyOneGetOne;      // B1G1 promotion
        private final boolean isHalfSecondItem;    // 50% off second item
        private final boolean hasFreeShipping;     // campaign includes free shipping

        public CampaignInfo(String campaignName, BigDecimal campaignDiscount, BigDecimal productDiscount,
                            BigDecimal loyaltyDiscount, boolean isBuyOneGetOne, boolean isHalfSecondItem,
                            boolean hasFreeShipping) {
            this.campaignName = campaignName;
            this.campaignDiscount = campaignDiscount != null ? campaignDiscount : BigDecimal.ZERO;
            this.productDiscount = productDiscount != null ? productDiscount : BigDecimal.ZERO;
            this.loyaltyDiscount = loyaltyDiscount != null ? loyaltyDiscount : BigDecimal.ZERO;
            this.isBuyOneGetOne = isBuyOneGetOne;
            this.isHalfSecondItem = isHalfSecondItem;
            this.hasFreeShipping = hasFreeShipping;
        }

        public String getCampaignName() {
            return campaignName;
        }

        public BigDecimal getCampaignDiscount() {
            return campaignDiscount;
        }

        public BigDecimal getProductDiscount() {
            return productDiscount;
        }

        public BigDecimal getLoyaltyDiscount() {
            return loyaltyDiscount;
        }

        public boolean isBuyOneGetOne() {
            return isBuyOneGetOne;
        }

        public boolean isHalfSecondItem() {
            return isHalfSecondItem;
        }

        public boolean hasFreeShipping() {
            return hasFreeShipping;
        }
    }
}
