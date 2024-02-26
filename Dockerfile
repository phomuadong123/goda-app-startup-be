# Use a Maven image as the base image
FROM maven:latest AS build

# Set the working directory in the container
WORKDIR /app

# Copy the project to the container
COPY . .

# Build the Maven project
RUN mvn clean package -DskipTests

# Create a new image with only the JAR file
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the build image to the current directory
COPY --from=build /app/target/*.jar ./app.jar

# Expose the port that the application will listen on
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "app.jar"]
