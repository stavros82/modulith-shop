package com.example.orders.adapters.in.rest;

import com.example.orders.adapters.in.rest.dto.CreateCustomerRequest;
import com.example.orders.adapters.in.rest.dto.CustomerResponse;
import com.example.orders.adapters.in.rest.mapper.CustomerMapper;
import com.example.orders.model.Customer;
import com.example.orders.service.CreateCustomerUseCase;
import com.example.orders.service.GetCustomerUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.example.orders.adapters.in.rest.mapper.CustomerMapper.toResponse;

@RestController
@RequestMapping("/customers")
public class CustomerRestController {

    private final CreateCustomerUseCase createCustomerUseCase;
    private final GetCustomerUseCase getCustomerUseCase;

    public CustomerRestController(CreateCustomerUseCase createCustomerUseCase, GetCustomerUseCase getCustomerUseCase) {
        this.createCustomerUseCase = createCustomerUseCase;
        this.getCustomerUseCase = getCustomerUseCase;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> create(@RequestBody CreateCustomerRequest request) {
        Customer customer = createCustomerUseCase.execute(request.name(), request.email(), request.phone());
        return ResponseEntity.ok(toResponse(customer));
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> get(@PathVariable("customerId") String customerId) {
        return ResponseEntity.ok(toResponse(getCustomerUseCase.execute(customerId)));
    }
}

