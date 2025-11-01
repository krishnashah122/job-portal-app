# Job Portal App

A comprehensive full-stack job portal application that connects job seekers with employers. The platform features a modern Angular frontend for user interaction and a robust Spring Boot backend providing RESTful APIs for job management, user authentication, and application processing.

## Table of Contents

- [Overview](#overview)
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
- [Deployment](#deployment)
- [Contributing](#contributing)
- [License](#license)

## Overview

The Job Portal is a complete solution for job searching and recruitment, consisting of:

- **Backend**: Spring Boot application providing REST APIs for user management, job postings, applications, and secure authentication
- **Frontend**: Angular single-page application offering intuitive interfaces for job seekers and employers

The application supports role-based access control with two main user types: Job Seekers (Candidates) and Employers.

## Features

### For Job Seekers
- **Browse Jobs**: View all available job postings on the home page
- **Search Jobs**: Find specific jobs using the search functionality
- **Apply to Jobs**: Submit applications with personal details, cover letters, and resume uploads
- **User Authentication**: Secure login and signup system

### For Employers
- **Post Jobs**: Create new job listings with detailed requirements including tech stack and experience
- **Manage Jobs**: Update existing job postings or delete them
- **View Applications**: Access and review all applications for their posted jobs

### General Features
- **Responsive Design**: Works seamlessly on desktop and mobile devices
- **Secure Authentication**: JWT-based authentication with role-based access control
- **Modern UI**: Built with Bootstrap for clean, professional styling
- **Real-time Updates**: Dynamic content loading and updates
- **File Upload**: Resume upload functionality for job applications
- **Search Functionality**: Keyword-based job search
- **CORS Support**: Configured for seamless frontend-backend integration

## Tech Stack

### Backend
- **Framework:** Spring Boot 3.5.3
- **Language:** Java 24
- **Database:** PostgreSQL
- **Security:** Spring Security, JWT (JJWT)
- **ORM:** Spring Data JPA / Hibernate
- **Build Tool:** Maven
- **Containerization:** Docker
- **Other:** Lombok, Multipart file handling

### Frontend
- **Framework**: Angular 20
- **Language**: TypeScript
- **Styling**: SCSS with Bootstrap 5.3.8
- **HTTP Client**: Angular HttpClient with interceptors
- **Forms**: Reactive Forms for robust form handling
- **Routing**: Angular Router for SPA navigation
- **Build Tool**: Angular CLI 20.1.0
- **Deployment**: Vercel

## Prerequisites

Before running this project, make sure you have the following installed:

- **Java 24 or higher** (for backend)
- **Maven 3.6+** (for backend)
- **Node.js (v18 or higher)** (for frontend)
- **npm** (comes with Node.js)
- **Angular CLI** (`npm install -g @angular/cli`)
- **PostgreSQL database**
- **Docker** (optional, for containerized deployment)

## Installation

### Backend Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd job-portal/job-portal-backend
   ```

2. **Install dependencies:**
   ```bash
   mvn clean install
   ```

### Frontend Setup

1. **Navigate to frontend directory:**
   ```bash
   cd ../job-portal-frontend
   ```

2. **Install dependencies:**
   ```bash
   npm install
   ```

## Configuration

### Backend Configuration

Update `job-portal-backend/src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/jobportaldb
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT Configuration
jwt.expiration=3600000
jwt.secret=your_jwt_secret_key

# CORS Configuration (for frontend integration)
# Configured in SecurityConfig.java for http://localhost:4200
```

### Frontend Configuration

The frontend uses environment-specific configurations in `job-portal-frontend/src/environments/`. Key settings include:

- `apiBaseUrl`: Backend API endpoint (default: production URL on Render)
- `token`: Cookie name for JWT token
- `role`: Cookie name for user role

For local development, update `environment.ts`:

```typescript
export const environment = {
  production: false,
  apiBaseUrl: 'http://localhost:8080/api/v1',
  token: 'token',
  role: 'role'
};
```

## Running the Application

### Running Backend

#### Using Maven
```bash
cd job-portal-backend
mvn spring-boot:run
```

The backend will start on `http://localhost:8080`.

#### Using Docker
```bash
cd job-portal-backend
docker build -t job-portal-backend .
docker run -p 8080:8080 job-portal-backend
```

### Running Frontend

#### Development Server
```bash
cd job-portal-frontend
ng serve
```

Or use the npm script:
```bash
npm start
```

The frontend will be available at `http://localhost:4200/`. The app will automatically reload when you make changes.

### Running Full Stack Locally

1. Start the backend (as above)
2. Start the frontend (as above)
3. Access the application at `http://localhost:4200/`

Ensure PostgreSQL is running and configured properly for the backend.

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
- **HTTP-Only Cookies:** JWT tokens stored securely in frontend

## Testing

### Backend Testing
Run tests using Maven:
```bash
cd job-portal-backend
mvn test
```

### Frontend Testing
Run unit tests:
```bash
cd job-portal-frontend
ng test
```

## Docker

### Backend Docker
The backend includes a multi-stage Dockerfile:

- **Build Stage:** Uses Maven to build the JAR
- **Runtime Stage:** Uses Eclipse Temurin JDK 24 for running the application

## Project Structure

```
job-portal-app/
├── job-portal-backend/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/jobportal/job_portal_backend/
│   │   │   │   ├── JobPortalApplication.java
│   │   │   │   ├── config/
│   │   │   │   │   └── SecurityConfig.java
│   │   │   │   ├── controller/
│   │   │   │   │   ├── AuthController.java
│   │   │   │   │   └── JobController.java
│   │   │   │   ├── dto/
│   │   │   │   │   ├── AuthRequest.java
│   │   │   │   │   ├── LoginResponse.java
│   │   │   │   │   ├── RegisterUserRequest.java
│   │   │   │   │   └── UserResponse.java
│   │   │   │   ├── filter/
│   │   │   │   │   └── JwtAuthFilter.java
│   │   │   │   ├── model/
│   │   │   │   │   ├── JobApplication.java
│   │   │   │   │   ├── JobApplicationDTO.java
│   │   │   │   │   ├── JobPost.java
│   │   │   │   │   ├── JobPostDTO.java
│   │   │   │   │   ├── Permissions.java
│   │   │   │   │   ├── Role.java
│   │   │   │   └── Users.java
│   │   │   │   ├── repo/
│   │   │   │   │   ├── JobApplicationRepo.java
│   │   │   │   │   ├── JobRepo.java
│   │   │   │   │   └── UserDetailsRepo.java
│   │   │   │   ├── service/
│   │   │   │   │   ├── JobService.java
│   │   │   │   │   ├── RoleInitializer.java
│   │   │   │   │   ├── UserAuthService.java
│   │   │   │   │   └── UserDetailsServiceImpl.java
│   │   │   │   └── utils/
│   │   │   │       ├── JobApplicationMapper.java
│   │   │   │       ├── JobPostMapper.java
│   │   │   │       └── JWTUtil.java
│   │   │   └── resources/
│   │   │       ├── application.properties
│   │   │       └── static/
│   │   └── test/
│   │       └── java/com/jobportal/job_portal_backend/
│   │           └── JobPortalApplicationTests.java
│   ├── Dockerfile
│   ├── pom.xml
│   └── README.md
├── job-portal-frontend/
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   │   ├── navbar/
│   │   │   │   └── job-post-card/
│   │   │   ├── models/
│   │   │   │   ├── JobPost.ts
│   │   │   │   └── JobApplication.ts
│   │   │   ├── pages/
│   │   │   │   ├── home/
│   │   │   │   ├── add-job/
│   │   │   │   ├── job-details/
│   │   │   │   ├── job-applications/
│   │   │   │   ├── search-results/
│   │   │   │   ├── login/
│   │   │   │   ├── signup/
│   │   │   │   ├── about/
│   │   │   │   ├── contact/
│   │   │   │   └── not-found/
│   │   │   ├── services/
│   │   │   │   ├── auth.ts
│   │   │   │   ├── auth-guard.ts
│   │   │   │   └── auth.interceptor.ts
│   │   │   ├── utils/
│   │   │   │   └── cookies-utils.ts
│   │   │   ├── app.config.ts
│   │   │   ├── app.routes.ts
│   │   │   ├── app.ts
│   │   │   └── app.html
│   │   ├── environments/
│   │   │   ├── environment.ts
│   │   │   └── environment.prod.ts
│   │   └── styles.scss
│   ├── angular.json
│   ├── package.json
│   ├── vercel.json
│   └── README.md
└── README.md (this file)
```

## Deployment

### Backend Deployment
The backend can be deployed on cloud platforms like Render, Heroku, or AWS. Ensure environment variables are set for database connection and JWT secret.

### Frontend Deployment
The frontend is configured for deployment on Vercel with the following setup in `vercel.json`:

- Build command: `npm run build`
- Output directory: `dist/job-portal-frontend/browser`
- SPA routing: All routes serve `index.html`

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

---

Built with ❤️ using Spring Boot and Angular
