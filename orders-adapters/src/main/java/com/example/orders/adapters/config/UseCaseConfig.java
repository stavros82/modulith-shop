package com.example.orders.adapters.config;

import com.example.orders.repository.OrderRepository;
import com.example.orders.service.CreateOrderUseCase;
import com.example.orders.service.GetOrderUseCase;
import com.example.orders.service.MarkOrderNotAvailableUseCase;
import com.example.orders.service.MarkOrderReservedUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCaseConfig {

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository repository) {
        return new CreateOrderUseCase(repository);
    }

    @Bean
    GetOrderUseCase getOrderUseCase(OrderRepository repository) {
        return new GetOrderUseCase(repository);
    }

    @Bean
    MarkOrderReservedUseCase markOrderReservedUseCase(OrderRepository repository) {
        return new MarkOrderReservedUseCase(repository);
    }

    @Bean
    MarkOrderNotAvailableUseCase markOrderNotAvailableUseCase(OrderRepository repository) {
        return new MarkOrderNotAvailableUseCase(repository);
    }
}

