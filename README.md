# Modulith Shop

A modular monolith sample shop application built with Java, Spring Boot, and Spring Modulith.

## Overview

This project is organized as a multi-module Maven build with separate domain and adapter modules for catalog, inventory, and orders, plus an `app` module that assembles the application. It follows a ports-and-adapters style structure and is designed to keep business logic separated from infrastructure concerns.

## Modules

| Module | Purpose |
|---|---|
| `catalog-domain` | Core catalog business logic and domain model. |
| `catalog-adapters` | Catalog-facing adapters such as REST, persistence, or integration components. |
| `inventory-domain` | Inventory business rules and domain model. |
| `inventory-adapters` | Inventory adapters and infrastructure integrations. |
| `orders-domain` | Order management business logic and domain model. |
| `orders-adapters` | Order adapters and infrastructure integrations. |
| `app` | Main application assembly and startup module. |

## Technology Stack

- Java 22
- Maven multi-module project
- Spring Boot 3.3.0
- Spring Modulith 1.2.0
- Postman collections for API flow testing

## Project Layout

```text
modulith-shop/
├── app/
├── catalog-domain/
├── catalog-adapters/
├── inventory-domain/
├── inventory-adapters/
├── orders-domain/
├── orders-adapters/
├── pom.xml
├── Catalog Flow.postman_collection.json
├── Inventory Flow.postman_collection.json
├── Orders Flow.postman_collection.json
└── Modulith-Shop-Adapters.postman_collection.json
```

## Build

Use the Maven wrapper from the project root:

```bash
./mvnw clean install
```

On Windows:

```bash
mvnw.cmd clean install
```

## Run

Start the assembled application from the root project once the modules are built:

```bash
./mvnw -pl app spring-boot:run
```

If the application module requires dependencies to be built first, run `./mvnw clean install` before starting it.

## API Testing

The repository includes Postman collections for the main application flows:

- `Catalog Flow.postman_collection.json`
- `Inventory Flow.postman_collection.json`
- `Orders Flow.postman_collection.json`
- `Modulith-Shop-Adapters.postman_collection.json`

A dedicated `POSTMAN_COLLECTION_README.md` file is also included in the repository to help with collection usage.

## Architecture Notes

The structure suggests these design goals:

- Clear boundaries between domain logic and adapters.
- A modular monolith approach instead of independently deployed microservices.
- Better maintainability by keeping each business area isolated.
- A codebase that is suitable for experimenting with Spring Modulith patterns.

## Next Improvements

- Add a `.github/workflows` CI pipeline for build and test validation.
- Rename the current repository `readme` file to `README.md` so GitHub renders it automatically.
- Add architecture diagrams for module dependencies.
- Document exposed endpoints and example requests.
- Add module-level and integration tests using Spring Modulith testing support.

## License

Add a license file if this project is intended for public reuse.
