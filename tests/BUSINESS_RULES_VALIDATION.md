# Business Rules Validation - Orders Module

## Overview
This document describes the Business Rules Validation implementation (section 8.6 of BRD) in the Orders module using Spring Validation Framework.

## Business Rules Implemented

### 1. Max 50 Items Per Order
**Rule:** `@MaxItems(value = 50)`
**Location:** `CreateOrderRequest.quantity` field
**Validation:** Quantity must not exceed 50 items

**Error Message:** "Order cannot exceed 50 items"

**Curl Example (Violation):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "prod-123",
    "quantity": 100,
    "shippingAddress": "123 Main St",
    "paymentMethod": "CARD",
    "weight": "20.0",
    "orderTotal": "5000.00"
  }'
```

**Curl Example (Valid):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "prod-123",
    "quantity": 25,
    "shippingAddress": "123 Main St",
    "paymentMethod": "CARD",
    "weight": "20.0",
    "orderTotal": "2500.00"
  }'
```

---

### 2. Max 30 kg Total Weight
**Rule:** `@MaxWeight(value = 30.0)`
**Location:** `CreateOrderRequest.weight` field
**Validation:** Weight must not exceed 30 kg

**Error Message:** "Order cannot exceed 30 kg"

**Curl Example (Violation):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "prod-456",
    "quantity": 5,
    "shippingAddress": "123 Main St",
    "paymentMethod": "CARD",
    "weight": "35.5",
    "orderTotal": "500.00"
  }'
```

---

### 3. No Alcohol Weekends (Mon-Fri Only)
**Rule:** `@NoAlcoholWeekends` (class-level annotation)
**Location:** `CreateOrderRequest` class
**Validation:** Alcohol products cannot be ordered on Saturday or Sunday

**Error Message:** "Alcohol products cannot be ordered on weekends"

**Detection:** Product ID containing "alcohol", "wine", or "beer"

**Curl Example (Violation - on weekend):**
```bash
# Saturday or Sunday only:
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "wine-cabernet",
    "quantity": 2,
    "shippingAddress": "123 Main St",
    "paymentMethod": "CARD",
    "weight": "4.0",
    "orderTotal": "50.00"
  }'
```

---

### 4. No Electronics to PO Box Addresses
**Rule:** `@NoElectronicsToPoBox` (class-level annotation)
**Location:** `CreateOrderRequest` class
**Validation:** Electronics cannot be shipped to PO box addresses

**Error Message:** "Electronics cannot be shipped to PO box addresses"

**Detection:** 
- PO box patterns: "p.o. box", "po box", "p.o box", "pobox"
- Electronics: product ID containing "electronic", "computer", "phone", "laptop"

**Curl Example (Violation):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "laptop-dell",
    "quantity": 1,
    "shippingAddress": "P.O. Box 123, New York, NY",
    "paymentMethod": "CARD",
    "weight": "2.0",
    "orderTotal": "1200.00"
  }'
```

**Curl Example (Valid - street address):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "laptop-dell",
    "quantity": 1,
    "shippingAddress": "123 Main Street, New York, NY",
    "paymentMethod": "CARD",
    "weight": "2.0",
    "orderTotal": "1200.00"
  }'
```

---

### 5. No COD (Cash on Delivery) Above €500
**Rule:** `@NoCodAbove500(threshold = 500.0)`
**Location:** `CreateOrderRequest` class
**Validation:** Cash on Delivery payment method not allowed for orders exceeding €500

**Error Message:** "Cash on Delivery not allowed for orders exceeding €500.00"

**Curl Example (Violation):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "prod-789",
    "quantity": 10,
    "shippingAddress": "123 Main St",
    "paymentMethod": "COD",
    "weight": "15.0",
    "orderTotal": "750.00"
  }'
```

**Curl Example (Valid - under €500):**
```bash
curl -X POST http://localhost:8080/orders \
  -H "Content-Type: application/json" \
  -d '{
    "productId": "prod-789",
    "quantity": 5,
    "shippingAddress": "123 Main St",
    "paymentMethod": "COD",
    "weight": "8.0",
    "orderTotal": "300.00"
  }'
```

---

## Validation Implementation Details

### Annotations Location
- **Package:** `com.example.orders.validation`
- **Annotations:**
  - `@MaxItems` + `MaxItemsValidator`
  - `@MaxWeight` + `MaxWeightValidator`
  - `@NoAlcoholWeekends` + `NoAlcoholWeekendsValidator`
  - `@NoElectronicsToPoBox` + `NoElectronicsToPoBoxValidator`
  - `@NoCodAbove500` + `NoCodAbove500Validator`

### DTO Location
- **Package:** `com.example.orders.adapters.in.rest.dto`
- **File:** `CreateOrderRequest.java`
- **Annotations Applied:** All 5 business rules

### Controller Location
- **Package:** `com.example.orders.adapters.in.rest`
- **File:** `OrdersRestController.java`
- **Validation Trigger:** `@Valid` on `@PostMapping` create method

---

## Spring Validation Framework Setup

### Dependencies Required
These are included in Spring Boot starter-web and spring-boot-starter-data-jpa:
- Jakarta Validation API (`jakarta.validation:jakarta.validation-api`)
- Hibernate Validator (implementation)

### Error Response Format
When validation fails, Spring returns a 400 Bad Request with error details:

```json
{
  "timestamp": "2026-06-08T10:30:00Z",
  "status": 400,
  "error": "Bad Request",
  "message": "Validation failed",
  "errors": [
    {
      "field": "quantity",
      "message": "Order cannot exceed 50 items",
      "rejectedValue": 100
    }
  ]
}
```

---

## How to Test

### 1. Build the Project
```powershell
cd C:\Users\Stavr\modulith-shop
.\mvnw.cmd clean install
```

### 2. Start the Application
```powershell
.\mvnw.cmd -pl app spring-boot:run
```

### 3. Run Curl Tests
Use the curl examples above to test each validation rule.

### 4. Use Postman
A collection with all validation examples has been added to:
- `tests/Modulith-Shop-Adapters.postman_collection.json` (Orders Adapters section)
- `tests/Orders Flow.postman_collection.json` (step 4a - Create Order with validation)

---

## Future Enhancements

### Product Metadata Caching
Currently, product type detection (alcohol, electronics) is based on product ID string matching. In production:
- Maintain a local cache of product metadata from Catalog events
- Query the cache in validators instead of string matching
- Update cache when `ProductCreatedEvent` or `ProductUpdatedEvent` is received

### Address Validation
Consider integrating with a third-party address validation service to properly detect PO boxes and validate addresses.

### Weight Calculation
The `weight` field is currently a simple decimal input. In production:
- Calculate total weight from product metadata + quantity
- Aggregate automatically instead of requiring client input

### COD Threshold Customization
Store the €500 threshold in a configuration file or database for easier updates without recompilation.

---

## Validation Chain Summary

```
Create Order Request
    ↓
@Valid Annotation Triggers
    ↓
Jakarta Validation Framework Processes
    ↓
1. @MaxItems.quantity       → MaxItemsValidator
2. @MaxWeight.weight        → MaxWeightValidator
3. @NoAlcoholWeekends       → NoAlcoholWeekendsValidator (class-level)
4. @NoElectronicsToPoBox    → NoElectronicsToPoBoxValidator (class-level)
5. @NoCodAbove500           → NoCodAbove500Validator (class-level)
    ↓
All Pass? → Proceed to Use Case
All Pass? → Return 400 Bad Request with error details
```

---

## Files Modified / Created

### Created:
- `orders-domain/src/main/java/com/example/orders/validation/MaxItems.java`
- `orders-domain/src/main/java/com/example/orders/validation/MaxItemsValidator.java`
- `orders-domain/src/main/java/com/example/orders/validation/MaxWeight.java`
- `orders-domain/src/main/java/com/example/orders/validation/MaxWeightValidator.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoAlcoholWeekends.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoAlcoholWeekendsValidator.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoElectronicsToPoBox.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoElectronicsToPoBoxValidator.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoCodAbove500.java`
- `orders-domain/src/main/java/com/example/orders/validation/NoCodAbove500Validator.java`

### Modified:
- `orders-adapters/src/main/java/com/example/orders/adapters/in/rest/dto/CreateOrderRequest.java` – Added fields and annotations
- `orders-adapters/src/main/java/com/example/orders/adapters/in/rest/OrdersRestController.java` – Added @Valid trigger

