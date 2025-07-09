# Use a Java 21 JDK image to run the Spring Boot app
FROM eclipse-temurin:21-jdk

# Set the working directory
WORKDIR /app

# Copy the jar file from the target folder to the working directory
COPY target/chat-app-backend-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 (optional, just a hint for users)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
