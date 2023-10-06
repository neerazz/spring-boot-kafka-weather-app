# Use the official OpenJDK 17 base image
FROM openjdk:17-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR into the image
COPY /build/libs/spring-kafka-city.jar /app/spring-kafka-city.jar

# Set the command to run your application using the java command
CMD ["java", "-jar", "/app/spring-kafka-city.jar"]
