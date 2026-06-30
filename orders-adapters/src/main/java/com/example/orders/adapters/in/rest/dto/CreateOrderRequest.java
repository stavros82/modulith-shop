package com.example.orders.adapters.in.rest.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;


public record CreateOrderRequest(
        @NotBlank(message = "Product ID cannot be blank")
        String productId,
        
        @NotNull(message = "Quantity cannot be null")

        BigDecimal quantity,
        
        String shippingAddress,
        
        @NotBlank(message = "Payment method cannot be blank")
        String paymentMethod,
        

        BigDecimal weight,
        
        BigDecimal unitPrice,
        String customerType,
        boolean isVip,
        String shippingRegion,
        String requestIp,
        String billingCountry,
        String shippingCountry,
        int previousFailedPayments
) {}

