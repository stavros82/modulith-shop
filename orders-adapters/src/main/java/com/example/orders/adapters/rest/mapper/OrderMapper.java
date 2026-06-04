package com.example.orders.adapters.rest.mapper;

import com.example.orders.adapters.rest.dto.OrderResponse;
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
                order.status().name(),
                order.reservationStatus().name()
        );
    }
}
