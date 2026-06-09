package com.example.orders.adapters.in.rest;

import com.example.orders.adapters.in.rest.dto.CreateOrderRequest;
import com.example.orders.adapters.in.rest.dto.OrderResponse;
import com.example.orders.model.Order;
import com.example.orders.service.CreateOrderUseCase;
import com.example.orders.service.GetOrderUseCase;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import com.example.orders.pricing.PricingContext;
import java.math.BigDecimal;
import static com.example.orders.adapters.in.rest.mapper.OrderMapper.toResponse;
import com.example.orders.exception.BusinessValidationException;

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
    public ResponseEntity<OrderResponse> create(@Valid @RequestBody CreateOrderRequest request) {
        // Build PricingContext from request
        BigDecimal unitPrice = request.unitPrice() != null ? request.unitPrice() : BigDecimal.ZERO;
        BigDecimal basePrice = unitPrice.multiply(request.quantity());
        
        PricingContext.CustomerType customerType = "B2B".equalsIgnoreCase(request.customerType()) 
                ? PricingContext.CustomerType.B2B 
                : PricingContext.CustomerType.B2C;

        PricingContext pricingContext = new PricingContext(
                basePrice,
                request.quantity(),
                unitPrice,
                customerType,
                request.isVip(),
                null, // campaign
                request.shippingRegion(),
                false, // isBlackFriday
                new BigDecimal("0.20"), // Default tax rate 20%
                new BigDecimal("5.00")  // Default shipping 5.00
        );

        Order order = createOrderUseCase.execute(
            request.productId(), 
            request.quantity(),
            request.shippingAddress(),
            request.paymentMethod(),
            request.weight(),
            pricingContext
        );
        return ResponseEntity.ok(toResponse(order));
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponse> get(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok(toResponse(getOrderUseCase.execute(orderId)));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationException(MethodArgumentNotValidException e) {
        Map<String, Object> body = new HashMap<>();
        var fieldMessages = e.getBindingResult().getFieldErrors().stream()
                .map(fe -> fe.getDefaultMessage())
                .collect(Collectors.toList());
        var globalMessages = e.getBindingResult().getGlobalErrors().stream()
                .map(ge -> ge.getDefaultMessage())
                .collect(Collectors.toList());
        fieldMessages.addAll(globalMessages);
        String combined = String.join(", ", fieldMessages);
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", combined);
        body.put("errors", fieldMessages);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, Object>> handleIllegalArgumentException(IllegalArgumentException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", e.getMessage());
        body.put("errors", new String[]{e.getMessage()});
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(BusinessValidationException.class)
    public ResponseEntity<Map<String, Object>> handleBusinessValidation(BusinessValidationException e) {
        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("error", "Bad Request");
        body.put("message", e.getMessage());
        body.put("errors", e.getErrors());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }


}

