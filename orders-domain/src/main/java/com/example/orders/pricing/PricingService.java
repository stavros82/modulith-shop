package com.example.orders.pricing;

import com.example.orders.service.CreateOrderCommand;
import java.math.BigDecimal;

public interface PricingService {
    BigDecimal calculateOrderTotal(CreateOrderCommand command);
}
