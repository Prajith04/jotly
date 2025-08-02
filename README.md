# Jotly

Jotly is a Spring Boot application following the MVC (Model-View-Controller) architecture. It provides RESTful endpoints and authentication using Spring Security.

## Project Structure

```
src/main/java/com/arcmind/jotly/
├── Config/
│   └── SecurityConfig.java         # Spring Security configuration
├── controller/
│   ├── AuthController.java        # Handles authentication (register, login)
│   └── JotlyController.java       # REST endpoints for Jotly resources
├── model/
│   ├── UserModel.java             # User entity/model
│   └── JotlyModel.java            # Jotly resource entity/model
├── repository/
│   ├── UserRepository.java        # User data access
│   └── JotlyRepository.java       # Jotly resource data access
├── service/
│   ├── UserService.java           # User business logic
│   └── JotlyService.java          # Jotly resource business logic
└── JotlyApplication.java          # Main application entry point
```

## MVC Structure

- **Model**: Classes in `model/` represent the application's data (e.g., `UserModel`, `JotlyModel`).
- **Repository**: Interfaces in `repository/` extend Spring Data JPA repositories for database operations.
- **Service**: Classes in `service/` contain business logic and interact with repositories.
- **Controller**: Classes in `controller/` handle HTTP requests and responses. `AuthController` manages authentication, while `JotlyController` exposes RESTful endpoints for Jotly resources.

## Authentication & Authorization

- **Spring Security** is configured in `Config/SecurityConfig.java`.
- Registration and login are handled by `AuthController`.
- Passwords are securely encoded using a `PasswordEncoder`.
- Access to resources is protected based on user roles and authentication status.

## REST Endpoints

- `JotlyController` exposes endpoints for CRUD operations on Jotly resources.
- Endpoints follow RESTful conventions (GET, POST, PUT, DELETE).

## Static Resources

- HTML files for login, registration, and index are located in `src/main/resources/static/`.

## Getting Started

1. Build the project: `./mvnw clean install`
2. Run the application: `./mvnw spring-boot:run`
3. Access the app at `http://localhost:8080/`

## License

MIT

