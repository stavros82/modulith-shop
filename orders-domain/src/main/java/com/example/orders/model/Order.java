package com.example.orders.model;

import java.math.BigDecimal;

public class Order {

    private final String id;
    private final String productId;
    private final BigDecimal quantity;

    private OrderStatus status;
    private StockReservationStatus reservationStatus;

    public Order(String id, String productId, BigDecimal quantity) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.status = OrderStatus.CREATED;
        this.reservationStatus = StockReservationStatus.PENDING;
    }

    public String id() {
        return id;
    }

    public String productId() {
        return productId;
    }

    public BigDecimal quantity() {
        return quantity;
    }

    public OrderStatus status() {
        return status;
    }

    public StockReservationStatus reservationStatus() {
        return reservationStatus;
    }

    public void markReserved() {
        this.reservationStatus = StockReservationStatus.RESERVED;
    }

    public void markNotAvailable() {
        this.reservationStatus = StockReservationStatus.NOT_AVAILABLE;
        this.status = OrderStatus.CANCELLED; // matches readme: auto-cancel on failure
    }
}

