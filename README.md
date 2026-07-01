# Task Manager API

A RESTful API for task management with JWT authentication, built with Spring Boot and PostgreSQL.

## Tech Stack

- **Java 17** + **Spring Boot 3**
- **Spring Security** + **JWT**
- **Spring Data JPA** + **Hibernate**
- **PostgreSQL**
- **Lombok**
- **Maven**

## Features

- User registration and login with JWT tokens
- Full CRUD for tasks
- Task statuses: `TODO`, `IN_PROGRESS`, `DONE`
- Input validation with meaningful error messages
- Global exception handling

## Architecture

The application follows a layered Spring Boot architecture:

- `controller`: REST endpoints for authentication and task operations
- `service`: business logic and task ownership checks
- `repository`: Spring Data JPA persistence
- `entity`: database models
- `dto`: request and response models
- `security`: JWT generation, validation, and request filtering
- `exception`: centralized API error handling

## Project Structure

```
src/main/java/com/zhalgas/taskmanager/
├── controller/         # REST endpoints
│   ├── AuthController.java
│   └── TaskController.java
├── service/            # Business logic
│   └── TaskService.java
├── repository/         # Spring Data JPA
│   ├── TaskRepository.java
│   └── UserRepository.java
├── entity/             # JPA entities
│   ├── Task.java
│   ├── TaskStatus.java
│   └── User.java
├── dto/                # Request/Response objects
│   ├── AuthRequest.java
│   ├── AuthResponse.java
│   ├── TaskRequest.java
│   └── TaskResponse.java
├── security/           # JWT logic
│   ├── JwtService.java
│   ├── JwtAuthFilter.java
│   └── UserDetailsServiceImpl.java
├── config/
│   └── SecurityConfig.java
└── exception/
    ├── TaskNotFoundException.java
    └── GlobalExceptionHandler.java
```

## How to Run

### Prerequisites

- Java 17+
- PostgreSQL 14+
- Maven 3.8+

### Setup

**1. Clone the repository**
```bash
git clone https://github.com/zhalgasv/TaskManagerApi.git
cd TaskManagerApi
```

**2. Create the database**
```bash
psql -U postgres -c "CREATE DATABASE task_manager_db;"
```

**3. Configure application.properties**
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_manager_db
spring.datasource.username=postgres
spring.datasource.password=YOUR_PASSWORD

jwt.secret=MySuperSecretKeyForJWTTokenGeneration12345
jwt.expiration=86400000
```

**4. Run the application**
```bash
mvn spring-boot:run
```

The server will start at `http://localhost:8080`

## API Endpoints

### Auth

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| POST | `/api/auth/register` | Register new user | No |
| POST | `/api/auth/login` | Login and get token | No |

### Tasks

| Method | Endpoint | Description | Auth required |
|--------|----------|-------------|---------------|
| GET | `/api/tasks` | Get all tasks | ✅ Yes |
| GET | `/api/tasks/{id}` | Get task by ID | ✅ Yes |
| POST | `/api/tasks` | Create task | ✅ Yes |
| PUT | `/api/tasks/{id}` | Update task | ✅ Yes |
| DELETE | `/api/tasks/{id}` | Delete task | ✅ Yes |

## API Examples

### Register
```bash
curl -X POST http://localhost:8080/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"adil","password":"1234"}'
```

Response:
```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9..."
}
```

### Login
```bash
curl -X POST http://localhost:8080/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"username":"adil","password":"1234"}'
```

### Create Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"title":"Learn Spring Boot","status":"IN_PROGRESS"}'
```

Response:
```json
{
  "id": 1,
  "title": "Learn Spring Boot",
  "description": null,
  "status": "IN_PROGRESS",
  "deadline": null,
  "createdAt": "2026-05-08T16:00:00"
}
```

### Get All Tasks
```bash
curl http://localhost:8080/api/tasks \
  -H "Authorization: Bearer YOUR_TOKEN"
```

### Validation Error Example
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer YOUR_TOKEN" \
  -d '{"title":""}'
```

Response:
```json
{
  "title": "Title is required"
}
```

## Tests

Run all tests:

```bash
mvn test
```

Current coverage includes a Spring context smoke test. Good next additions are service tests for task ownership and controller tests for secured endpoints.

## Author

**Adil Zhalgas** — [LinkedIn](https://www.linkedin.com/in/adil-zhalgas-79a305324) · [GitHub](https://github.com/zhalgasv)
