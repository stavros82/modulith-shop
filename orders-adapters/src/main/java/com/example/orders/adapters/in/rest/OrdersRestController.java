package com.example.orders.adapters.in.rest;

import com.example.orders.adapters.in.rest.dto.CreateOrderRequest;
import com.example.orders.adapters.in.rest.dto.OrderResponse;
import com.example.orders.adapters.in.rest.service.OrderManagementService;
import com.example.orders.adapters.in.rest.service.OrderQueryService;
import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderCommand;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import static com.example.orders.adapters.in.rest.mapper.OrderMapper.toResponse;


@RestController
@RequestMapping("/orders")
public class OrdersRestController {

    private final OrderManagementService orderManagementService;
    private final OrderQueryService orderQueryService;

    public OrdersRestController(
            OrderManagementService orderManagementService,
            OrderQueryService orderQueryService

    ) {
        this.orderManagementService = orderManagementService;
        this.orderQueryService = orderQueryService;

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
        Order order = orderManagementService.createOrder(command);

        return ResponseEntity.ok(toResponse(order));
    }


    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> get(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(toResponse(orderQueryService.getOrder(orderId)));
    }
}
