package com.example.orders.adapters.in.rest;

import com.example.orders.adapters.in.rest.dto.CreateOrderRequest;
import com.example.orders.adapters.in.rest.dto.OrderResponse;
import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderCommand;
import com.example.orders.service.CreateOrderUseCase;
import com.example.orders.service.GetOrderUseCase;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.example.orders.adapters.in.rest.mapper.OrderMapper.toResponse;


@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;

    public OrdersRestController(
            CreateOrderUseCase createOrderUseCase,
            GetOrderUseCase getOrderUseCase

    ) {
        this.createOrderUseCase = createOrderUseCase;
        this.getOrderUseCase = getOrderUseCase;

    }

    @PostMapping
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        // Controller just maps the Request to the Command
        CreateOrderCommand command = new CreateOrderCommand(
                request.productId(),
                request.quantity(),
                request.shippingAddress(),
                request.paymentMethod(),
                request.weight(),
                request.unitPrice(),
                request.customerType(),
                request.isVip(),
                request.shippingRegion(),
                request.requestIp(),
                request.billingCountry(),
                request.shippingCountry(),
                request.previousFailedPayments()
        );

        // Call Use Case with the single Command parameter
        Order order = createOrderUseCase.execute(command);

        return ResponseEntity.ok(toResponse(order));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> get(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(toResponse(getOrderUseCase.execute(orderId)));
    }





}

