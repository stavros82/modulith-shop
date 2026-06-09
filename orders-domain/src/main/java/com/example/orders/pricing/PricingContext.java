package com.example.orders.pricing;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Context object containing all information needed for pricing calculation.
 * Passed to pricing strategies to determine price.
 */
public class PricingContext {
    private final BigDecimal basePrice; // subtotal before discounts
    private final BigDecimal quantity;
    private final BigDecimal unitPrice;
    private final CustomerType customerType; // B2C or B2B
    private final boolean isVipCustomer;
    private final CampaignInfo campaign; // null if no campaign
    private final String shippingRegion; // for tax/shipping calculation
    private final boolean isBlackFridayPeriod;
    private final BigDecimal taxRate; // e.g., 0.19 for 19% VAT
    private final BigDecimal standardShippingCost;

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

    // Getters
    public BigDecimal getBasePrice() {
        return basePrice;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public CustomerType getCustomerType() {
        return customerType;
    }

    public boolean isVipCustomer() {
        return isVipCustomer;
    }

    public CampaignInfo getCampaign() {
        return campaign;
    }

    public String getShippingRegion() {
        return shippingRegion;
    }

    public boolean isBlackFridayPeriod() {
        return isBlackFridayPeriod;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public BigDecimal getStandardShippingCost() {
        return standardShippingCost;
    }

    /**
     * Customer type classification for pricing
     */
    public enum CustomerType {
        B2C, // Business to Consumer
        B2B  // Business to Business
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

