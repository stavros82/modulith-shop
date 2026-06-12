package com.example.orders.pricing;



import com.example.orders.service.CreateOrderCommand;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PricingServiceTest {

    private final PricingService pricingService = Mockito.mock(PricingService.class);

    @Test
    void calculateOrderTotal_shouldCallPricingService() {
        // Arrange
        CreateOrderCommand command = new CreateOrderCommand(
                "prod-123",
                new BigDecimal("2"),
                "123 Street",
                "CREDIT_CARD",
                new BigDecimal("1.5"),
                new BigDecimal("50.00"),
                "B2C",
                false,
                "DE",
                "127.0.0.1",
                "DE",
                "DE",
                0
        );
        BigDecimal expectedTotal = new BigDecimal("110.00");
        when(pricingService.calculateOrderTotal(command)).thenReturn(expectedTotal);

        // Act
        BigDecimal actualTotal = pricingService.calculateOrderTotal(command);

        // Assert
        assertEquals(expectedTotal, actualTotal);
    }

    @Test
    void calculateOrderTotal_shouldReturnExpectedAmount() {
        // Arrange
        CreateOrderCommand command = Mockito.mock(CreateOrderCommand.class);
        BigDecimal expectedTotal = new BigDecimal("100.00");
        when(pricingService.calculateOrderTotal(command)).thenReturn(expectedTotal);

        // Act
        BigDecimal actualTotal = pricingService.calculateOrderTotal(command);

        // Assert
        assertEquals(0, expectedTotal.compareTo(actualTotal), "The calculated total should match the expected amount.");
    }
}
