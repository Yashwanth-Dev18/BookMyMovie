FROM openjdk:11-jdk-slim

WORKDIR /app

# Copy gradle wrapper and build files
COPY gradlew .
COPY gradle/wrapper gradle/wrapper
COPY build.gradle .
COPY settings.gradle .

# Copy source code
COPY src src

# Make gradlew executable
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build

# Run tests and generate coverage
RUN ./gradlew test jacocoTestReport

# Copy coverage report to accessible location
RUN cp -r build/reports/jacoco/test/html /coverage-report

# Default command - run the application
CMD ["./gradlew", "run"]