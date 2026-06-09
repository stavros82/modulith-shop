package com.example.orders.adapters.config;

import com.example.orders.event.OrderEventPublisher;
import com.example.orders.pricing.PricingStrategy;
import com.example.orders.pricing.PricingStrategySelector;
import com.example.orders.repository.CustomerRepository;
import com.example.orders.repository.OrderRepository;
import com.example.orders.service.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.Validator;

@Configuration
public class OrderUseCaseConfig {

    @Bean
    public PricingStrategy pricingStrategy() {
        return new PricingStrategySelector();
    }

    @Bean
    public ValidatorFactory validatorFactory() {
        return Validation.buildDefaultValidatorFactory();
    }

    @Bean
    public Validator validator(ValidatorFactory validatorFactory) {
        return validatorFactory.getValidator();
    }

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, OrderEventPublisher eventPublisher, Validator validator, PricingStrategy pricingStrategy) {
        return new CreateOrderUseCase(repository, eventPublisher, validator, pricingStrategy);
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

    @Bean
    CreateCustomerUseCase createCustomerUseCase(CustomerRepository repository) {
        return new CreateCustomerUseCase(repository);
    }

    @Bean
    GetCustomerUseCase getCustomerUseCase(CustomerRepository repository) {
        return new GetCustomerUseCase(repository);
    }
}

