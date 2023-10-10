# Kafka City's Grand Weather Tower: How to Build a Real-Time Data Streaming System That Will Blow Your Mind

---

"Dear adventurers of the data realm! Welcome back to the final chapter of our Kafka City trilogy. If you've been journeying with us from the start, you've witnessed the rise and intricacies of this magnificent data metropolis. If you're just stepping onto these cobbled streets, I recommend taking a look back at our earlier adventures to truly appreciate the marvel we're about to construct:

1. **[Journey through the Data Metropolis with Superhero of Data Streaming](https://medium.com/@neerazz/journey-through-the-data-metropolis-with-superhero-of-data-streaming-kafka-e729777a5646)**
2. **[Navigating the Kafka Cityscape: A Deep Dive into the Superhero’s Lair](https://medium.com/@neerazz/navigating-the-kafka-cityscape-a-deep-dive-into-the-superheros-lair-921121ada94f)**
2. **[Kafka City's Grand Weather Tower: How to Build a Real-Time Data Streaming System That Will Blow Your Mind](https://medium.com/@neerazz/navigating-the-kafka-cityscape-a-deep-dive-into-the-superheros-lair-921121ada94f)**

Now, brace yourselves as we ascend the tallest structure in Kafka City: The Grand Weather Tower!

---


Welcome to the Spring Boot Kafka Weather App! This application showcases the power of Kafka in a real-time data streaming system. The application is designed as a part of the Kafka City trilogy, and this repository holds the implementation of Kafka City's Grand Weather Tower.

## Prerequisites

1. Java 11 or higher
2. Docker and Docker Compose (if you wish to run the app with Docker)

## Local Setup Instructions

1. Clone the repository:
   ```bash
   git clone https://github.com/neerazz/spring-boot-kafka-weather-app.git
   cd spring-boot-kafka-weather-app
   ```

2. Build the project using Gradle:
   ```bash
   ./gradlew clean build
   ```

3. Run the application:
   ```bash
   ./gradlew bootRun
   ```

## Docker Setup Instructions

1. Ensure you've built the project using Gradle as mentioned in the local setup.

2. Build the Docker image:
   ```bash
   docker build -t spring-boot-kafka-weather-app .
   ```

3. Run the application using Docker Compose (ensure you have a `docker-compose.yml` file):
   ```bash
   docker-compose up
   ```

## Features

- Real-time weather data broadcasting using Kafka.
- Storage of weather data in H2 database.
- Real-time data streaming showcasing Kafka producers and consumers.

## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.