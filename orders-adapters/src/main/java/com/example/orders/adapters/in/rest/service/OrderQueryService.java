package com.example.orders.adapters.in.rest.service;

import com.example.orders.model.Order;
import com.example.orders.service.GetOrderUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderQueryService {

    private final GetOrderUseCase getOrderUseCase;

    public OrderQueryService(GetOrderUseCase getOrderUseCase) {
        this.getOrderUseCase = getOrderUseCase;
    }

    @Transactional(readOnly = true)
    public Order getOrder(String orderId) {
        return getOrderUseCase.execute(orderId);
    }
}
