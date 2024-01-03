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

  ## Set up Kafka locally
>  ### Download Kafka binaries
>   - Create a directory named Apache under C:\FAST and extract contents of the above zip file.
>
>   ### Setup environment variables
>    - Ensure JAVA_HOME is configured with JDK 1.8+ and the PATH variable is updated.

## Start Zookeeper
>   Kafka uses ZooKeeper to manage the cluster. ZooKeeper is used to coordinate the brokers/cluster topology.
>
>   Open Command Prompt:
>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>   - Run  `bin\windows\zookeeper-server-start.bat config\zookeeper.properties` to start the Zookeeper.

## Start Kafka Server
>
>   Open a second Command Prompt:
>
>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>   - Run `bin\windows\kafka-server-start.bat config\server.properties` to start the Kafka Server.

## Create Kafka Topic
>   A Topic is a category/feed name to which messages are stored and published. Messages are byte arrays that can store any object in any format. All Kafka messages are organized into topics
>
>   Open a third Command Prompt:
>
>    ### Creating a topic for String Message's
>>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>>   - Run `bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test` which will create 'test' topic.
       >>    ![Kafka Topic Creation](image/Kafka_topic_creation.JPG)
>
>    ### Creating second topic for User type message (JSON)
>>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>>   - Run `bin\windows\kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic jsontest` which will create 'jsontest' topic.
>
>
>   We can now see that topic if we run the list topic command:
>
>   - Run `bin\windows\kafka-topics.bat --list --bootstrap-server localhost:9092`
      >       - test
>       - jsontest

## Publish messages to the Kafka Topic

>   Publish messages "hello" and "hi" under topic 'test'
>
>   In third Command Prompt:
>
>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>   - Run `kafka-console-producer.bat --broker-list localhost:9092 --topic test`. This will connect to 'test' topic.
>   - Type 'Hello' and hit Return/Enter. You have published a message to Kafka!
>   - Type 'Hi' and hit Return/Enter. You have published a message to Kafka!
>   - Try publishing another message...

## Consume messages from the Kafka Topic

>   Start consumer to receive message from topic.
>
>   In fourth Command Prompt:
>   - Run `cd D:\Apache\kafka_2.12-2.5.0` to go to the Kafka scripts directory
>   - Run `set classpath=.` This will add the Kafka java libraries in class path.
>   - Run `kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning`

## API Endpoint

#### Introduction
This endpoint records weather data at a specific location and time.

#### Endpoint
- **URL**: `http:localhost:8080`
- **Method**: `POST`

#### Headers
- `Content-Type`: application/json

#### Request Body
- `longitude` (float): Longitude of the location.
- `latitude` (float): Latitude of the location.
- `weatherInCelsius` (float): Temperature in Celsius.
- `timestamp` (string): ISO 8601 formatted date and time.

#### Request
```bash
curl --location 'http:localhost:8080' \
--header 'Content-Type: application/json' \
--data '{
    "longitude": 12.9716,
    "latitude": 77.5946,
    "weatherInCelsius": 30.0,
    "timestamp": "2021-08-01T12:00:00"
}'
```

#### Success Response
- **Code**: 200 OK
- **Content**: `{ success: true, message: "Weather update sent to tower!" }`

#### Error Response
- **Code**: 400 BAD REQUEST
- **Content**: `{ error: "Invalid request data" }`

#### Notes
- Ensure that all numeric values are within acceptable ranges.
- Timestamps must be current and conform to the ISO 8601 standard.


## Contributing

Pull requests are welcome! For major changes, please open an issue first to discuss what you would like to change.