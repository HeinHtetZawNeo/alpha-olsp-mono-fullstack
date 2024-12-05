# Use an official OpenJDK 21 image as the base
FROM openjdk:21-jdk-slim

LABEL authors="heinhtetzaw"

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/AlphaOnlineSystemApplication-0.0.1.jar AlphaOnlineSystemApplication-0.0.1.jar
COPY .env /app/.env

# Expose the port that the service runs on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "AlphaOnlineSystemApplication-0.0.1.jar"]