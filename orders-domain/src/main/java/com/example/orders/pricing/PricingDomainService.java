package com.example.orders.pricing;

import com.example.orders.model.CustomerType;
import com.example.orders.service.CreateOrderCommand;

import java.math.BigDecimal;


public class PricingDomainService implements PricingService {
    private final PricingStrategy pricingStrategy;

    public PricingDomainService(PricingStrategy pricingStrategy) {
        this.pricingStrategy = pricingStrategy;
    }

    @Override
    public BigDecimal calculateOrderTotal(CreateOrderCommand command) {
        // Logic to build the context is now hidden here!
        PricingContext context = new PricingContext(
            command.unitPrice().multiply(command.quantity()),
            command.quantity(),
            command.unitPrice(),
            "B2B".equalsIgnoreCase(command.customerType()) ? CustomerType.B2B : CustomerType.B2C,
            command.isVip(),
            null,
            command.shippingRegion(),
            false, 
            new BigDecimal("0.20"), // Tax rate (Inject this via config if possible)
            new BigDecimal("5.00")  // Shipping cost
        );

        return pricingStrategy.calculate(context).finalPrice();
    }
}
