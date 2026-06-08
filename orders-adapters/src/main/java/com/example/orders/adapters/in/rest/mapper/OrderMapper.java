package com.example.orders.adapters.in.rest.mapper;

import com.example.orders.adapters.in.rest.dto.OrderResponse;
import com.example.orders.model.Order;

public class OrderMapper {
    private OrderMapper() {
        /* This utility class should not be instantiated */
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.id(),
                order.productId(),
                order.quantity(),
                order.shippingAddress(),
                order.paymentMethod(),
                order.weight(),
                order.orderTotal(),
                order.status().name(),
                order.reservationStatus().name()
        );
    }
}
