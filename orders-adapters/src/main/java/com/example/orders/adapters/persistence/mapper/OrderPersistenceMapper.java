package com.example.orders.adapters.persistence.mapper;

import com.example.orders.adapters.persistence.entity.OrderJpaEntity;
import com.example.orders.model.Order;
import com.example.orders.model.OrderStatus;
import com.example.orders.model.StockReservationStatus;

public class OrderPersistenceMapper {

    private OrderPersistenceMapper() {}

    public static Order toDomain(OrderJpaEntity e) {
        if (e == null) {
            return null;
        }

        Order order = new Order(e.getId(), e.getProductId(), e.getQuantity());

        if (e.getReservationStatus() != null) {
            // restore status by calling methods (we keep Order fairly simple)
            StockReservationStatus rs = StockReservationStatus.valueOf(e.getReservationStatus());
            if (rs == StockReservationStatus.RESERVED) {
                order.markReserved();
            } else if (rs == StockReservationStatus.NOT_AVAILABLE) {
                order.markNotAvailable();
            }
        }

        // status is derived in current domain based on reservation; keep this for future expansion
        if (e.getStatus() != null && OrderStatus.valueOf(e.getStatus()) == OrderStatus.CANCELLED) {
            order.markNotAvailable();
        }

        return order;
    }

    public static OrderJpaEntity toEntity(Order o) {
        if (o == null) {
            return null;
        }
        OrderJpaEntity e = new OrderJpaEntity();
        e.setId(o.id());
        e.setProductId(o.productId());
        e.setQuantity(o.quantity());
        e.setStatus(o.status().name());
        e.setReservationStatus(o.reservationStatus().name());
        return e;
    }
}

