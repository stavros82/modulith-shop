package com.example.orders.adapters.in.rest.dto;

import java.math.BigDecimal;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;
import com.example.orders.validation.MaxItems;
import com.example.orders.validation.MaxWeight;

public record CreateOrderRequest(
        @NotBlank(message = "Product ID cannot be blank")
        String productId,
        
        @NotNull(message = "Quantity cannot be null")
        @MaxItems(value = 50, message = "Order cannot exceed 50 items")
        BigDecimal quantity,
        
        String shippingAddress,
        
        @NotBlank(message = "Payment method cannot be blank")
        String paymentMethod,
        
        @MaxWeight(value = 30.0, message = "Order cannot exceed 30 kg")
        BigDecimal weight,
        
        BigDecimal orderTotal
) {}

