# Use a Java 21 JDK image to build the Spring Boot app
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

# Expose port 8080
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "/app/target/chat-app-backend-0.0.1-SNAPSHOT.jar"]
