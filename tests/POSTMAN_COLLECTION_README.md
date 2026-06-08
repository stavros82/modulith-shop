# Modulith Shop - Postman Collection Setup

## Overview
This Postman collection contains all the GraphQL API endpoints for the Modulith Shop adapters.

## Prerequisites
- Spring Boot running on `http://localhost:8080`
- GraphQL endpoint at `/graphql` (automatically configured via Spring GraphQL)

## Files Created

### 1. Configuration Files
- **app/src/main/resources/application.properties** - Spring Boot configuration with GraphQL settings
- **app/src/main/resources/graphql/schema.graphql** - GraphQL schema definition

### 2. Postman Collection
- **Modulith-Shop-Adapters.postman_collection.json** - Contains all API requests

## How to Run

### Step 1: Build the Project
```bash
cd C:\Users\Stavr\modulith-shop
.\mvnw.cmd clean install
```

### Step 2: Run the Application
```bash
cd app
..\mvnw.cmd spring-boot:run
```

The application should start on `http://localhost:8080`

### Step 3: Verify GraphQL Endpoint
Navigate to: `http://localhost:8080/graphql`

You should see the GraphiQL interface.

### Step 4: Import Postman Collection
1. Open Postman
2. Click "Import"
3. Select "Modulith-Shop-Adapters.postman_collection.json"
4. The collection is now ready to test

## API Endpoints

### Catalog Adapters (GraphQL)

#### Queries
- **Get Product by ID** - `POST /graphql` - Retrieve a single product
- **Get All Products** - `POST /graphql` - List all products
- **Get All Categories** - `POST /graphql` - List all categories
- **Get Catalog Report** - `POST /graphql` - Aggregated stats per product and category (reviews, ratings, quality issues, averages)

#### Mutations
- **Create Category** - `POST /graphql` - Add a category (optional `parentId` for sub-categories)
- **Create Product** - `POST /graphql` - Add a new product (use `categoryId` from Create Category)
- **Update Product** - `POST /graphql` - Update existing product
- **Add Review** - `POST /graphql` - Add product review
- **Report Quality Issue** - `POST /graphql` - Report quality issues

### Inventory Adapters (REST)

- **Inventory Flow.postman_collection.json**: End-to-end flow that shows Catalog → Inventory event-driven sync (create product, replenish stock, report quality issue, verify quarantine)
- **Get Inventory Product** - `GET /inventory/products/{productId}` - Read local inventory state
- **Replenish Stock (Admin)** - `POST /inventory/products/{productId}/replenish?quantity=100` - Increase stock for demos

### Orders Adapters (REST)

- **Orders Flow.postman_collection.json**: End-to-end flow that shows Orders → Inventory → Orders stock reservation (create product, replenish stock, create order, verify reservation status)
- **Create Order** - `POST /orders` - Create an order (publishes `OrderCreatedEvent`)
- **Get Order** - `GET /orders/{orderId}` - Read local order state (should become `RESERVED` or `NOT_AVAILABLE`)

## Testing

Each request in the Postman collection includes:
- Pre-configured GraphQL query/mutation
- Sample variables for testing
- Detailed descriptions

Simply select a request and click "Send" to execute it.

## Troubleshooting

### 404 Error on /graphql
✅ **Fixed** - Ensure:
1. `application.properties` exists in `app/src/main/resources/`
2. `schema.graphql` exists in `app/src/main/resources/graphql/`
3. Spring Boot is running: `http://localhost:8080`

### Port 8080 Already in Use
Change the port in `application.properties`:
```properties
server.port=9090
```
Then update the Postman collection base URL.

## Configuration Details

### SpringBoot Configuration (application.properties)
```properties
# GraphQL is enabled by default
spring.graphql.graphiql.enabled=true
spring.graphql.path=/graphql
```

### Database
- Uses in-memory H2 database
- Auto-creates tables on startup
- Schema: `src/main/resources/graphql/schema.graphql`

## Notes
- Inventory and Orders adapters are placeholders in the collection
- Extend the collection as you add more endpoints to those modules

