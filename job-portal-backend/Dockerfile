# Build stage with Maven and JDK 20
FROM maven:3.9.9-eclipse-temurin-24 AS build
WORKDIR /job-portal-app

# Copy pom.xml and download dependencies first (improves caching)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy project source code
COPY src ./src

# Build the jar file without running tests
RUN mvn clean package -DskipTests

# Run stage with JDK 20 runtime
FROM eclipse-temurin:24-jdk
WORKDIR /job-portal-app

# Copy the built jar from build stage
COPY --from=build /job-portal-app/target/job-portal-0.0.1-SNAPSHOT.jar job-portal-app.jar

# Expose default Spring Boot port
EXPOSE 8080

# Run the Spring Boot jar
ENTRYPOINT ["java","-jar","job-portal-app.jar"]
