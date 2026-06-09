package com.example.orders.pricing;

/**
 * A selector strategy that picks the most appropriate strategy based on the context.
 */
public class PricingStrategySelector implements PricingStrategy {

    private final StandardPricingStrategy standardStrategy;
    private final VipPricingStrategy vipStrategy;
    private final B2bPricingStrategy b2bStrategy;

    public PricingStrategySelector() {
        this.standardStrategy = new StandardPricingStrategy();
        this.vipStrategy = new VipPricingStrategy();
        this.b2bStrategy = new B2bPricingStrategy();
    }

    @Override
    public PricingResult calculate(PricingContext context) {
        if (context == null) {
            throw new IllegalArgumentException("PricingContext cannot be null");
        }
        if (context.customerType() == PricingContext.CustomerType.B2B) {
            return b2bStrategy.calculate(context);
        } else if (context.isVipCustomer()) {
            return vipStrategy.calculate(context);
        } else {
            return standardStrategy.calculate(context);
        }
    }
}
