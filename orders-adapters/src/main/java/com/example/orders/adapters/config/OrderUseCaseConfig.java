package com.example.orders.adapters.config;

import com.example.orders.event.OrderEventPublisher;
import com.example.orders.pricing.PricingDomainService;
import com.example.orders.pricing.PricingService;
import com.example.orders.pricing.PricingStrategy;
import com.example.orders.pricing.PricingStrategySelector;
import com.example.orders.repository.CustomerRepository;
import com.example.orders.repository.OrderRepository;
import com.example.orders.service.*;
import com.example.orders.validation.ValidationPipeline;
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
    public PricingService pricingService(PricingStrategy pricingStrategy) {
        // HERE is where the 'new' happens!
        return new PricingDomainService(pricingStrategy);
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
    public ValidationPipeline validationPipeline() {
        return new ValidationPipeline();
    }

    @Bean
    CreateOrderUseCase createOrderUseCase(OrderRepository repository, OrderEventPublisher eventPublisher, Validator validator, PricingService pricingService, ValidationPipeline validationPipeline) {
        return new CreateOrderUseCase(repository, eventPublisher, validator, pricingService, validationPipeline);
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

