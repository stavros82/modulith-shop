package com.example.orders.adapters.in.rest.service;

import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderCommand;
import com.example.orders.service.CreateOrderUseCase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderManagementService {

    private final CreateOrderUseCase createOrderUseCase;

    public OrderManagementService(CreateOrderUseCase createOrderUseCase) {
        this.createOrderUseCase = createOrderUseCase;
    }

    @Transactional
    public Order createOrder(CreateOrderCommand command) {
        return createOrderUseCase.execute(command);
    }
}
