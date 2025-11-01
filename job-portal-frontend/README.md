# Job Portal Frontend

A modern, responsive job portal web application built with Angular 20, designed to connect job seekers with employers. The application provides a seamless experience for browsing jobs, applying to positions, and managing job postings.

## Features

### For Job Seekers
- **Browse Jobs**: View all available job postings on the home page
- **Search Jobs**: Find specific jobs using the search functionality in the navbar
- **Apply to Jobs**: Submit applications with personal details, cover letters, and resume uploads
- **User Authentication**: Secure login and signup system

### For Employers
- **Post Jobs**: Create new job listings with detailed requirements
- **Manage Jobs**: Update existing job postings
- **View Applications**: Access and review all applications for their posted jobs
- **Delete Jobs**: Remove job postings as needed

### General Features
- **Responsive Design**: Works seamlessly on desktop and mobile devices
- **Secure Authentication**: JWT-based authentication with role-based access control
- **Modern UI**: Built with Bootstrap for clean, professional styling
- **Real-time Updates**: Dynamic content loading and updates

## Technology Stack

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

- Node.js (v18 or higher)
- npm (comes with Node.js)
- Angular CLI (`npm install -g @angular/cli`)

## Getting Started

### Installation

1. Clone the repository:
```bash
git clone <repository-url>
cd job-portal-frontend
```

2. Install dependencies:
```bash
npm install
```

### Development Server

To start the development server:

```bash
ng serve
```

Or use the npm script:
```bash
npm start
```

The application will be available at `http://localhost:4200/`. The app will automatically reload when you make changes to the source files.

### Building for Production

To build the project for production:

```bash
ng build
```

Or use the npm script:
```bash
npm run build
```

The build artifacts will be stored in the `dist/job-portal-frontend/browser/` directory.

### Running Tests

To execute unit tests:

```bash
ng test
```

Or use the npm script:
```bash
npm test
```

## Project Structure

```
src/
├── app/
│   ├── components/
│   │   ├── navbar/           # Navigation component with search
│   │   └── job-post-card/    # Reusable job card component
│   ├── models/
│   │   ├── JobPost.ts        # Job posting interface
│   │   └── JobApplication.ts # Job application interface
│   ├── pages/
│   │   ├── home/             # Job listings page
│   │   ├── add-job/          # Job creation/update form
│   │   ├── job-details/      # Job details and application form
│   │   ├── job-applications/ # Applications management (employers)
│   │   ├── search-results/   # Search results page
│   │   ├── login/            # User login page
│   │   ├── signup/           # User registration page
│   │   ├── about/            # About page
│   │   ├── contact/          # Contact page
│   │   └── not-found/        # 404 page
│   ├── services/
│   │   ├── auth.ts           # Authentication service
│   │   ├── auth-guard.ts     # Route guard (basic implementation)
│   │   └── auth.interceptor.ts # HTTP interceptor for auth headers
│   ├── utils/
│   │   └── cookies-utils.ts  # Cookie utility functions
│   ├── app.config.ts         # Application configuration
│   ├── app.routes.ts         # Route definitions
│   ├── app.ts                # Root component
│   └── app.html              # Root template
├── environments/
│   ├── environment.ts        # Development environment config
│   └── environment.prod.ts   # Production environment config
└── styles.scss               # Global styles
```

## Configuration

### Environment Variables

The application uses environment-specific configurations in `src/environments/`. Key settings include:

- `apiBaseUrl`: Backend API endpoint (currently set to Render deployment)
- `token`: Cookie name for JWT token
- `role`: Cookie name for user role

### API Endpoints

Key API endpoints used:
- `POST /register` - User registration
- `POST /login` - User authentication
- `GET /jobs` - Fetch all jobs
- `GET /jobs/keyword/{keyword}` - Search jobs
- `POST /job` - Create job posting
- `PUT /job/update/{id}` - Update job posting
- `DELETE /job/{id}` - Delete job posting
- `GET /job/{id}` - Get job details
- `POST /apply/{jobId}` - Submit job application
- `GET /applications` - Get job applications (employers only)

## Authentication & Authorization

The application implements role-based access control:

- **JOB_SEEKER**: Can browse jobs, apply to positions, view own applications
- **EMPLOYER**: Can post jobs, manage their postings, view applications

Authentication is handled via JWT tokens stored in HTTP-only cookies with automatic header injection via HTTP interceptor.

## Deployment

The application is configured for deployment on Vercel with the following setup in `vercel.json`:

- Build command: `npm run build`
- Output directory: `dist/job-portal-frontend/browser`
- SPA routing: All routes serve `index.html`

## Development Guidelines

### Code Style
- Uses Prettier for code formatting (configured in `package.json`)
- Follows Angular style guide
- Component selectors use `app-` prefix

### File Naming
- Components: `component-name.ts`, `component-name.html`, `component-name.scss`
- Services: `service-name.ts`
- Models: `ModelName.ts` (PascalCase)

### Best Practices
- Reactive Forms for complex form handling
- Dependency injection for services
- Signal-based state management where appropriate
- Proper error handling and user feedback

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Support

For support or questions, please contact the development team or create an issue in the repository.

---

Built with ❤️ using Angular 20
