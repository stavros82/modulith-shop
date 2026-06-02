package com.example.orders.adapters.rest;

import com.example.orders.adapters.rest.dto.CreateOrderRequest;
import com.example.orders.adapters.rest.dto.OrderResponse;
import com.example.orders.event.OrderCreatedEvent;
import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderUseCase;
import com.example.orders.service.GetOrderUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final ApplicationEventPublisher eventPublisher;

    public OrdersRestController(
            CreateOrderUseCase createOrderUseCase,
            GetOrderUseCase getOrderUseCase,
            ApplicationEventPublisher eventPublisher
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;
        this.eventPublisher = eventPublisher;
    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request) {
        Order order = createOrderUseCase.execute(request.productId(), request.quantity());
        eventPublisher.publishEvent(new OrderCreatedEvent(order.id(), order.productId(), order.quantity()));
        return ResponseEntity.ok(toResponse(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> get(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(toResponse(getOrderUseCase.execute(orderId)));
    }

    private static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.id(),
                order.productId(),
                order.quantity(),
                order.status().name(),
                order.reservationStatus().name()
        );
    }
}

