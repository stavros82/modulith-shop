package com.example.orders.adapters.in.rest;

import com.example.orders.adapters.in.rest.dto.CreateOrderRequest;
import com.example.orders.adapters.in.rest.dto.OrderResponse;
import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderUseCase;
import com.example.orders.service.GetOrderUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.example.orders.adapters.in.rest.mapper.OrderMapper.toResponse;

@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrdersRestController(
            CreateOrderUseCase createOrderUseCase,
            GetOrderUseCase getOrderUseCase,
            ApplicationEventPublisher eventPublisher
    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;

    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@RequestBody CreateOrderRequest request) {
        Order order = createOrderUseCase.execute(request.productId(), request.quantity());
        return ResponseEntity.ok(toResponse(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> get(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(toResponse(getOrderUseCase.execute(orderId)));
    }


}

