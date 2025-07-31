# Use Java 24 base image (if not available, use Java 21 instead for now)
FROM eclipse-temurin:24-jdk


# Set working directory inside container
WORKDIR /app

# Copy JAR file into container
COPY target/jotly-0.0.1-SNAPSHOT.jar app.jar
# Expose default Spring Boot port
EXPOSE 8080

# Run the JAR
ENTRYPOINT ["java", "-jar", "app.jar"]
