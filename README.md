# library-management-system
This project provides a simple REST API for a library management system, developed using Spring Boot. The API allows for efficient management of books, authors, and copies, enabling operations such as adding, updating, deleting, and retrieving information.

## Features

## Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/RehanChalana/library-management-system.git
   cd library-management-system
   ```
2. **Install Docker Desktop**
   https://www.docker.com/products/docker-desktop/
4. **Configure the application properties:**
   ```bash
   spring.datasource.url=jdbc:postgresql://<HOST>:<PORT>/<DATABASE_NAME>
   spring.datasource.username=<USERNAME>
   spring.datasource.password=<PASSWORD>
   ```
   
5. **Build the project:**
   ```bash
   ./mvnw clean install

## API Documentation 
1. **Start The Application**
   ```bash
   ./mvnw spring-boot:run
   ```
2.  Open your web browser and navigate to http://localhost:8080/swagger-ui/index.html


