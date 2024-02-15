# Metrics-Challenge
API that receives a set of metrics and weights associated with each metric and returns the list of ordered products

## Technologies

- Java 17
- Spring Boot
- MapStruct

## Getting Started

These instructions will get you a copy of the projects up and running on your local machine for development and testing purposes

### Running the application locally

```
mvn clean package
mvn spring-boot:run
```

### Testing

```
mvn clean test
```

## Requirements

- The application uses the H2 database to run as in-memory database and store the products
  - http://localhost:8080/h2-console
- Swagger has been enabled to visualize and interact with the API’s resources
  - http://localhost:8080/swagger-ui.html
- Actuator health endpoint available
  - http://localhost:8080/actuator/health
- Hexagonal Architecture applied
  - More details here: https://rbailen.medium.com/hexagonal-architecture-with-spring-boot-74e93030eba3
  
## Proposed improvements

- Development based on API First
- Dockerize the application
- Security with OAuth 2.1
- Evolutionary database design with Liquibase or Flyway
