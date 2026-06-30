package com.example.orders.validation;

import com.example.orders.model.CustomerType;

import java.math.BigDecimal;

public record OrderValidationContext(
    String orderId,
    CustomerType customerType,
    PaymentMethod paymentMethod,
    BigDecimal totalAmount,
    String requestIp,
    String billingCountry,
    String shippingCountry,
    int previousFailedPayments,
    String productId,
    BigDecimal quantity,
    BigDecimal weight,
    String shippingAddress
) {

    public enum PaymentMethod {
        CARD, PAYPAL, COD, INVOICE
    }
}
