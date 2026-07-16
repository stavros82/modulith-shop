# Modulith-shop refactor: move event publication to adapters/services

## Step 1: Inventory current event publication points
- Search across modules for `*EventPublisher*`, `publish...`, and event publishing usages in:
  - `orders-domain` use cases
  - `catalog-domain` use cases
  - `inventory-domain` use cases (and any “sync”/integration use cases)
- Record the exact call sites and affected event types.

## Step 2: Orders refactor
- Remove event publisher dependencies from `orders-domain` use cases:
  - Ensure use cases no longer reference `OrderEventPublisher` (and do not publish events).
- Introduce/extend adapter-side responsibilities:
  - In `orders-adapters/.../OrderManagementService`, publish `OrderCreatedEvent` (and any other orders-related events) *after* the use case completes.
- Keep domain event types (`OrderCreatedEvent`, etc.) if they represent payload contracts; only move the *publishing* behavior.

## Step 3: Catalog refactor
- Remove `ProductEventPublisher` usage from `catalog-domain` use cases.
- Publish catalog events from `catalog-adapters` services (GraphQL management services) instead:
  - Map use case outcome → event → publish in adapter.

## Step 4: Inventory refactor
- Remove any publisher usage from `inventory-domain` (no publishing from domain/use cases).
- Ensure `inventory-adapters` listeners remain unchanged (or adjust wiring only if needed due to signature changes).

## Step 5: Listener/publisher wiring cleanup
- Update adapter-side publisher implementations (e.g. `OrderEventPublisherImpl`) to be the only place where events are published.
- Update adapter event listeners if they rely on old publisher location/signature.

## Step 6: Build & tests
- Run Maven tests for each module (and root):
  - `mvn test`
- Fix compilation issues and any failing architecture/module-boundary tests.
