# Job Portal Backend

A comprehensive Spring Boot-based backend application for a job portal system. This application provides RESTful APIs for user authentication, job posting, job applications, and role-based access control.

## Table of Contents

- [Features](#features)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Configuration](#configuration)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Database Schema](#database-schema)
- [Security](#security)
- [Testing](#testing)
- [Docker](#docker)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Features

- **User Authentication & Authorization**
  - JWT-based authentication
  - Role-based access control (Employer, Candidate)
  - Secure password encoding

- **Job Management**
  - Create, read, update, and delete job posts
  - Search jobs by keywords
  - Job posting with tech stack and experience requirements

- **Job Applications**
  - Apply for jobs with resume upload
  - View applications (for employers)
  - File upload handling for resumes

- **Security Features**
  - CORS configuration for frontend integration
  - Stateless session management
  - Method-level security with @PreAuthorize

- **Data Management**
  - PostgreSQL database integration
  - JPA/Hibernate ORM
  - Automatic data loading for demo purposes

## Tech Stack

- **Framework:** Spring Boot 3.5.3
- **Language:** Java 24
- **Database:** PostgreSQL
- **Security:** Spring Security, JWT (JJWT)
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Containerization:** Docker
- **Other:** Lombok, Multipart file handling

## Prerequisites

- Java 24 or higher
- Maven 3.6+
- PostgreSQL database
- Docker (optional, for containerized deployment)

## Installation

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd job-portal-backend
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

## Configuration

### Database Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/jobportaldb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.expiration=3600000
jwt.secret=your_jwt_secret_key
```

### OAuth2 Configuration (Optional)

For Google/GitHub OAuth2 login, uncomment and configure in `application.properties`:

```properties
spring.security.oauth2.client.registration.google.client-id=your_google_client_id
spring.security.oauth2.client.registration.google.client-secret=your_google_client_secret

spring.security.oauth2.client.registration.github.client-id=your_github_client_id
spring.security.oauth2.client.registration.github.client-secret=your_github_client_secret
```

## Running the Application

### Using Maven

```bash
mvn spring-boot:run
```

The application will start on `http://localhost:8080`.

### Using Docker

1. **Build the Docker image:**
   ```bash
   docker build -t job-portal-backend .
   ```

2. **Run the container:**
   ```bash
   docker run -p 8080:8080 job-portal-backend
   ```

## API Documentation

### Authentication Endpoints

- **POST** `/api/v1/register` - Register a new user
  - Body: `RegisterUserRequest` (username, password, role)
  - Roles: EMPLOYER, CANDIDATE

- **POST** `/api/v1/login` - User login
  - Body: `AuthRequest` (username, password)
  - Returns: JWT token and user details

### Job Endpoints

- **GET** `/api/v1/jobs` - Get all job posts
- **GET** `/api/v1/job/{jobId}` - Get job by ID
- **GET** `/api/v1/jobs/keyword/{keyword}` - Search jobs by keyword
- **POST** `/api/v1/job` - Create new job post (EMPLOYER only)
  - Body: `JobPost`
- **PUT** `/api/v1/job/update/{jobId}` - Update job post (EMPLOYER only)
- **DELETE** `/api/v1/job/{jobId}` - Delete job post (EMPLOYER only)

### Application Endpoints

- **GET** `/api/v1/applications` - Get all applications (EMPLOYER only)
- **POST** `/api/v1/apply/{jobId}` - Apply for a job (CANDIDATE only)
  - Form data: name, email, coverLetter (optional), resume (file)

### Utility Endpoints

- **GET** `/api/v1/home` - Home endpoint
- **GET** `/api/v1/load` - Load sample data

## Database Schema

### Users Table
- id (Primary Key)
- username
- password (encoded)
- role (EMPLOYER/CANDIDATE)

### JobPost Table
- jobId (Primary Key)
- jobProfile
- jobDesc
- requiredExperience
- techStack (JSON array)

### Applications Table
- applicationId (Primary Key)
- name
- email
- coverLetter
- resumeFileName
- resumeFilePath
- appliedAt
- job_id (Foreign Key to JobPost)

## Security

- **JWT Authentication:** Bearer token required for protected endpoints
- **Roles and Permissions:**
  - EMPLOYER: CREATE_JOBPOST, READ_JOBPOST, UPDATE_JOBPOST, DELETE_JOBPOST, VIEW_APPLICATIONS
  - CANDIDATE: READ_JOBPOST, APPLY_JOB
- **CORS:** Configured for Angular frontend at `http://localhost:4200`
- **Password Encoding:** DelegatingPasswordEncoder

## Testing

Run tests using Maven:

```bash
mvn test
```

Current tests include basic context loading tests.

## Docker

The application includes a multi-stage Dockerfile:

- **Build Stage:** Uses Maven to build the JAR
- **Runtime Stage:** Uses Eclipse Temurin JDK 24 for running the application

## Project Structure

```
src/
├── main/
│   ├── java/com/jobportal/job_portal_backend/
│   │   ├── JobPortalApplication.java
│   │   ├── config/
│   │   │   └── SecurityConfig.java
│   │   ├── controller/
│   │   │   ├── AuthController.java
│   │   │   └── JobController.java
│   │   ├── dto/
│   │   │   ├── AuthRequest.java
│   │   │   ├── LoginResponse.java
│   │   │   ├── RegisterUserRequest.java
│   │   │   └── UserResponse.java
│   │   ├── filter/
│   │   │   └── JwtAuthFilter.java
│   │   ├── model/
│   │   │   ├── JobApplication.java
│   │   │   ├── JobApplicationDTO.java
│   │   │   ├── JobPost.java
│   │   │   ├── JobPostDTO.java
│   │   │   ├── Permissions.java
│   │   │   ├── Role.java
│   │   │   └── Users.java
│   │   ├── repo/
│   │   │   ├── JobApplicationRepo.java
│   │   │   ├── JobRepo.java
│   │   │   └── UserDetailsRepo.java
│   │   ├── service/
│   │   │   ├── JobService.java
│   │   │   ├── RoleInitializer.java
│   │   │   ├── UserAuthService.java
│   │   │   └── UserDetailsServiceImpl.java
│   │   └── utils/
│   │       ├── JobApplicationMapper.java
│   │       ├── JobPostMapper.java
│   │       └── JWTUtil.java
│   └── resources/
│       ├── application.properties
│       └── static/
├── test/
│   └── java/com/jobportal/job_portal_backend/
│       └── JobPortalApplicationTests.java
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Commit your changes
4. Push to the branch
5. Create a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.
