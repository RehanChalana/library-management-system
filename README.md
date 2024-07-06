# library-management-system
This project provides a simple REST API for a library management system, developed using Spring Boot. The API allows for efficient management of books, authors, and copies, enabling operations such as adding, updating, deleting, and retrieving information.

## Key Features

1. **Full CRUD Functionality**
   - Comprehensive Create, Read, Update, and Delete operations
2. **Data Transfer Object (DTO) Pattern**
   - Efficient data exchange between layers
3. **PostgreSQL Database Integration**
   - Robust and scalable data storage solution
4. **Advanced Error Handling**
   - Custom exceptions for specific scenarios
   - Global error handling for consistent responses
5. **Comprehensive Testing Suite**
   - Integration tests using TestContainers for realistic database interactions
   - Unit tests with Mockito and JUnit 5 for thorough code coverage
6. **API Documentation**
   - Interactive API documentation using Swagger/OpenAPI
7. **Best Practices Implementation**
   - Adherence to software design principles and industry standards

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/RehanChalana/library-management-system.git
   ```
2. **Running PostgreSQL for Integration Testing with Docker Desktop** <br>
   To run a PostgreSQL image for integration testing, you need to use Docker Desktop. You can download Docker Desktop from the official website: [Docker Desktop](https://www.docker.com/products/docker-desktop/)
4. **Configure the application properties:** <br>
To connect your application to the PostgreSQL database, update the following properties in your application configuration file:
   ```properties
   spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>
   spring.datasource.username=<USERNAME>
   spring.datasource.password=<PASSWORD>
   ```
5. **Start The Application**
   ```bash
   ./mvnw spring-boot:run

## API Documentation 
1. **Start The Application**
   ```bash
   ./mvnw spring-boot:run
   ```
2.   **Access API Documentation** <br>
     Open your web browser and navigate to <br>
     http://localhost:8080/swagger-ui/index.html
     


