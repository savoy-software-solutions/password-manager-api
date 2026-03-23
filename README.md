# Password Manager REST API

A secure RESTful API built with Java and Spring Boot that allows users to
store and retrieve encrypted passwords. Implements JWT authentication and
AES-256 encryption for maximum security.

## Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT (JJWT 0.11.5)
- AES-256 Encryption
- PostgreSQL
- Maven

## Security Features

- Passwords are never stored in plain text
- All stored passwords are encrypted with AES-256 before saving to the database
- User login passwords are hashed with BCrypt
- JWT tokens expire after 24 hours
- All routes except /auth/** require a valid token
- Data ownership validation ensures users can only access their own entries

## Error Handling

All errors return a consistent JSON response:
```json
{
    "status": 404,
    "error": "Not Found",
    "message": "Password entry not found",
    "timestamp": "2026-03-23T13:45:36"
}
```

| Exception | Status Code |
|---|---|
| Resource not found | 404 Not Found |
| Username not found | 404 Not Found |
| Bad request | 400 Bad Request |
| Unexpected error | 500 Internal Server Error |

## How It Works

1. User registers and receives a JWT token
2. User logs in and receives a JWT token
3. Token is sent with every request in the Authorization header
4. JWT filter validates the token and identifies the user
5. Passwords are encrypted with AES-256 before storing
6. Passwords are decrypted when retrieved by the owner

## Getting Started

### Prerequisites
- Java 21
- PostgreSQL
- Maven

### Database Setup
1. Create a PostgreSQL database named `password_manager`
2. Update `src/main/resources/application.properties`:
```
spring.datasource.url=jdbc:postgresql://localhost:5432/password_manager
spring.datasource.username=your_username
spring.datasource.password=your_postgres_password
jwt.secret=your_jwt_secret
jwt.expiration=86400000
encryption.key=your_32_character_encryption_key
```

### Running the App
```
./mvnw spring-boot:run
```
The API will start at `http://localhost:8080`

## API Endpoints

### Register
```
POST /auth/register
Content-Type: application/json

{
    "username": "johnsavoy",
    "email": "john@example.com",
    "password": "password123"
}
```

### Login
```
POST /auth/login
Content-Type: application/json

{
    "username": "johnsavoy",
    "password": "password123"
}
```

### Get all password entries
```
GET /api/passwords
Authorization: Bearer <your_token>
```

### Get single password entry
```
GET /api/passwords/{id}
Authorization: Bearer <your_token>
```

### Save a new password entry
```
POST /api/passwords
Authorization: Bearer <your_token>
Content-Type: application/json

{
    "siteName": "Gmail",
    "siteUrl": "gmail.com",
    "siteUsername": "john@gmail.com",
    "password": "myGmailPassword"
}
```

### Update a password entry
```
PUT /api/passwords/{id}
Authorization: Bearer <your_token>
Content-Type: application/json

{
    "siteName": "Gmail",
    "siteUrl": "gmail.com",
    "siteUsername": "john@gmail.com",
    "password": "updatedPassword"
}
```

### Delete a password entry
```
DELETE /api/passwords/{id}
Authorization: Bearer <your_token>
```

## Encryption vs Hashing

| | BCrypt (login passwords) | AES-256 (stored passwords) |
|---|---|---|
| Reversible | No | Yes |
| Used for | Login verification | Password retrieval |
| Why | Never need original | User needs to retrieve it |

## Author
Johnathan Savoy  
[linkedin.com/in/john-savoy](https://linkedin.com/in/john-savoy)