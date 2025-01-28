# PatientRegistrationAPI

The **PatientRegistrationAPI** is a Java-based RESTful API built with Spring Boot for managing patient registration. It includes secure user authentication and authorization using JWT tokens and BCrypt password hashing. The API is fully documented using **Swagger/OpenAPI** for seamless exploration and testing.

---

## Features

- **Swagger UI**: Integrated API documentation accessible via a web interface.
- **User Authentication**: Secure login and registration with encrypted passwords (BCrypt) and JWT token generation.
- **Patient Management**: CRUD operations for patient records, including activation and deactivation.
- **Validation and Error Handling**: Ensures proper request handling and provides meaningful error messages.
- **API Security**: Stateless authentication with Spring Security and custom filters.

---

## API Workflow

1. **User Registration**: Create a user account by registering.
2. **Login**: Obtain a JWT token upon successful authentication.
3. **Access API**: Use the token to access protected endpoints.

---

## Data Models

### User

```json
{
  "login": "username",
  "password": "password"
}
```

### Patient

```json
{
  "id": 1,
  "name": "string",
  "birthDate": "1999-01-28",
  "bloodGroup": "A+",
  "phoneNumber": "string",
  "email": "string",
  "address": "string"
}
```

---

## Endpoints

### Authentication

| Method | Endpoint          | Description              | Authentication |
|--------|-------------------|--------------------------|----------------|
| POST   | `/login/register` | Register a new user      | No             |
| POST   | `/login`          | Login and get JWT token  | No             |

### Patient Management

| Method | Endpoint              | Description                        | Authentication |
|--------|-----------------------|------------------------------------|----------------|
| POST   | `/patients`           | Register a new patient             | Yes            |
| GET    | `/patients`           | List all active patients           | Yes            |
| GET    | `/patients/{id}`      | Get details of a specific patient  | Yes            |
| PUT    | `/patients`           | Update patient information         | Yes            |
| PUT    | `/patients/activate/{id}` | Activate a patient                 | Yes            |
| DELETE | `/patients/deactivate/{id}` | Deactivate a patient               | Yes            |
| DELETE | `/patients/{id}`      | Delete a patient                   | Yes            |

---

## Documentation

- The API documentation is available at:  
  **`http://localhost:8080/swagger-ui.html`**  
  Explore and test the endpoints directly from this interface.

---

## Error Handling

The API provides meaningful HTTP status codes and error messages:

- **400**: Bad Request (e.g., invalid input)
- **403**: Unauthorized access
- **404**: Resource not found
- **500**: Internal server error

---

## Security

- **BCrypt Password Encryption**: Ensures secure storage of passwords.
- **JWT Authentication**: Stateless authentication for API access.
- **Custom Security Filter**: Processes and validates JWT tokens.

---

## Prerequisites

- **Java 17+**
- **Spring Boot 3.x**
- **Maven 3.x**
- **MySQL**
