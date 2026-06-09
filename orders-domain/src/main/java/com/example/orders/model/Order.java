package com.example.orders.model;

import com.example.orders.validation.NoCodAbove500;

import java.math.BigDecimal;


@NoCodAbove500
public class Order {

    private final String id;
    private final String productId;
    private final BigDecimal quantity;
    private final String shippingAddress;
    private final String paymentMethod;
    private final BigDecimal weight;
    private final BigDecimal orderTotal;

    private OrderStatus status;
    private StockReservationStatus reservationStatus;

    public Order(String id, String productId, BigDecimal quantity, String shippingAddress, 
                 String paymentMethod, BigDecimal weight, BigDecimal orderTotal) {
        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.shippingAddress = shippingAddress;
        this.paymentMethod = paymentMethod;
        this.weight = weight;
        this.orderTotal = orderTotal;
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

    public String shippingAddress() {
        return shippingAddress;
    }

    public String paymentMethod() {
        return paymentMethod;
    }

    public BigDecimal weight() {
        return weight;
    }

    public BigDecimal orderTotal() {
        return orderTotal;
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

