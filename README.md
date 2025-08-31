# Spring Boot API with PostgreSQL

[![Build Status](https://github.com/fayo-labs/spring-api-mysql/workflows/SonarCloud/badge.svg)](https://github.com/fayo-labs/spring-api-mysql/actions)
[![codecov](https://codecov.io/gh/fayo-labs/spring-api-mysql/branch/master/graph/badge.svg)](https://codecov.io/gh/fayo-labs/spring-api-mysql)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=fayo-labs_spring-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=fayo-labs_spring-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=fayo-labs_spring-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=fayo-labs_spring-api)

A modern Spring Boot 3.3.3 REST API application with PostgreSQL database support, Docker Compose integration, and comprehensive testing using Testcontainers.

## Features

- **Spring Boot 3.3.3** with Java 21
- **PostgreSQL** database with Docker Compose support
- **Testcontainers** for integration testing
- **JaCoCo** test coverage reporting
- **SonarCloud** code quality analysis
- **GitHub Actions** CI/CD pipeline
- **RESTful API** for Topics and Courses management

## Technologies Used

- Java 21
- Spring Boot 3.3.3
- Spring Data JPA
- PostgreSQL 16
- Docker & Docker Compose
- Testcontainers
- JUnit 5
- Mockito
- JaCoCo (Test Coverage)
- Maven
- GitHub Actions

## Getting Started

### Prerequisites

- Java 21
- Docker and Docker Compose
- Maven 3.6+

### Running the Application

1. **Clone the repository**

   ```bash
   git clone https://github.com/fayo-labs/spring-api-mysql.git
   cd spring-api-mysql
   ```

2. **Start PostgreSQL with Docker Compose**

   ```bash
   docker-compose up -d
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`

### Running Tests

**Run all tests:**

```bash
mvn test
```

**Run tests with coverage:**

```bash
mvn clean test jacoco:report
```

**View coverage report:**
Open `target/site/jacoco/index.html` in your browser

### API Endpoints

#### Topics

- `GET /topics` - Get all topics
- `GET /topics/{id}` - Get topic by ID
- `POST /topics` - Create new topic
- `PUT /topics/{id}` - Update topic
- `DELETE /topics/{id}` - Delete topic

#### Courses

- `GET /topics/{topicId}/courses` - Get courses for a topic
- `GET /topics/{topicId}/courses/{id}` - Get specific course
- `POST /topics/{topicId}/courses` - Create new course
- `PUT /topics/{topicId}/courses/{id}` - Update course
- `DELETE /topics/{topicId}/courses/{id}` - Delete course

### Example API Usage

**Create a topic:**

```bash
curl -X POST http://localhost:8080/topics \
  -H "Content-Type: application/json" \
  -d '{"name": "Spring Boot", "description": "Learn Spring Boot framework"}'
```

**Get all topics:**

```bash
curl http://localhost:8080/topics
```

## Database Configuration

The application uses PostgreSQL as the primary database. Configuration is handled through:

- **Development**: Docker Compose (`compose.yaml`)
- **Testing**: Testcontainers with PostgreSQL
- **Production**: Environment variables

### Environment Variables

- `SPRING_DATASOURCE_URL` - Database URL
- `SPRING_DATASOURCE_USERNAME` - Database username
- `SPRING_DATASOURCE_PASSWORD` - Database password

## Testing Strategy

### Unit Tests

- Service layer testing with Mockito
- Repository layer testing with `@DataJpaTest`

### Integration Tests

- Full application context testing with Testcontainers
- Controller testing with MockMvc
- Database integration testing

### Test Coverage

- Minimum coverage threshold: 50%
- JaCoCo reports generated automatically
- Coverage uploaded to Codecov and SonarCloud

## CI/CD Pipeline

The project uses GitHub Actions for:

- **Build and Test**: Compile code and run all tests
- **Test Coverage**: Generate and upload coverage reports
- **Code Quality**: SonarCloud analysis
- **Docker Support**: PostgreSQL service for testing

## Docker Support

### Development Environment

```bash
# Start PostgreSQL
docker-compose up -d postgres

# Stop services
docker-compose down
```

### Production Deployment

```bash
# Build application
mvn clean package

# Run with Docker Compose
docker-compose up --build
```

## Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add some amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

## Code Quality

- **SonarCloud**: Automated code quality analysis
- **Spotless**: Code formatting with Google Java Format
- **JaCoCo**: Test coverage reporting
- **GitHub Actions**: Automated CI/CD pipeline

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

## Acknowledgments

- Spring Boot team for the excellent framework
- Testcontainers for making integration testing easier
- PostgreSQL for the robust database system
