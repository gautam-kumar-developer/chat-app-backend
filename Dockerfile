# Use a Java 21 JDK image to build the Spring Boot app
FROM eclipse-temurin:21-jdk AS build

# Set the working directory
WORKDIR /app

#copy the jar file from the target folder to working directory
COPY target/chat-app-backend-0.0.1-SNAPSHOT.jar chat-app-backend-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Specify the command to run the application
ENTRYPOINT ["java", "-jar", "chat-app-backend-0.0.1-SNAPSHOT.jar"]