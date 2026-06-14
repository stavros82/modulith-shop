package com.example.orders.pricing;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class VipPricingStrategyTest {

    private VipPricingStrategy vipStrategy;
    private PricingContext context;

    @BeforeEach
    void setUp() {
        vipStrategy = new VipPricingStrategy();
        context = mock(PricingContext.class);
        when(context.basePrice()).thenReturn(BigDecimal.valueOf(100));
        when(context.taxRate()).thenReturn(BigDecimal.valueOf(0.2));
        when(context.isVipCustomer()).thenReturn(true);
    }

    @Test
    void testCalculate() {
        PricingResult result = vipStrategy.calculate(context);
        assertEquals(new BigDecimal(102).setScale(2), result.finalPrice());
    }
}