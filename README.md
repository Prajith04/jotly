# Jotly

Jotly is a note-taking and management application built with Spring Boot. It allows users to register, log in, and securely create, view, update, and delete personal notes (called "Jotlys") via a RESTful API. The app enforces authentication and authorization using Spring Security, ensuring that only authenticated users can access and manage their own notes.

## Main Functionalities

- User registration and login with secure password handling
- Authentication and authorization using Spring Security
- Create, read, update, and delete personal notes (Jotlys)
- RESTful API endpoints for all note operations
- Each user can only access and manage their own notes
- HTML pages for login, registration, and index
- Role-based access control (optional for future extension)

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

- **Spring Security** is configured in `Config/SecurityConfig.java` to protect endpoints and manage user sessions.
- Registration and login are handled by `AuthController`, with password encoding for security.
- Only authenticated users can access Jotly resources, and user roles can be enforced for further authorization.

## REST Endpoints

The following endpoints are exposed by `JotlyController`:

- `POST /api/notes` — Create a new note
- `GET /api/notes` — Get all notes for the authenticated user, with optional sorting (`sort` and `direction` query params)
- `GET /api/notes/search?title=...` — Search notes by title
- `GET /api/notes/{id}` — Get a specific note by its ID
- `PUT /api/notes/{id}` — Update a note by its ID
- `DELETE /api/notes/{id}` — Delete a note by its ID

## Static Resources

- HTML files for login, registration, and index are located in `src/main/resources/static/`.

## Getting Started

1. Build the project: `./mvnw clean install`
2. Run the application: `./mvnw spring-boot:run`
3. Access the app at `http://localhost:8080/`

## License

MIT
