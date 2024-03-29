-- Create the Table

DROP TABLE IF EXISTS WEATHER_ENTITY;

CREATE TABLE `weather_entity`
(
    id                 BIGINT AUTO_INCREMENT PRIMARY KEY,
    city               VARCHAR(255),
    latitude           float(53),
    longitude          float(53),
    weather_in_celsius float(53),
    timestamp          datetime(6),
    description        VARCHAR(255)
);


--  Insert data to the table.

INSERT INTO `weather_entity` (city, latitude, longitude, weather_in_celsius, timestamp, description)
VALUES ('New York', 40.7128, -74.0060, 25.0, '2023-01-01 12:00:00', 'Sunny'),
       ('Los Angeles', 34.0522, -118.2437, 28.0, '2023-01-02 12:00:00', 'Cloudy'),
       ('Chicago', 41.8781, -87.6298, 10.0, '2023-01-03 12:00:00', 'Snowy'),
       ('Houston', 29.7604, -95.3698, 35.0, '2023-01-04 12:00:00', 'Rainy'),
       ('Phoenix', 33.4484, -112.0740, 40.0, '2023-01-05 12:00:00', 'Sunny'),
       ('San Francisco', 37.7749, -122.4194, 17.0, '2023-01-06 12:00:00', 'Foggy'),
       ('Seattle', 47.6062, -122.3321, 13.0, '2023-01-07 12:00:00', 'Rainy'),
       ('Denver', 39.7392, -104.9903, 16.0, '2023-01-08 12:00:00', 'Sunny'),
       ('Miami', 25.7617, -80.1918, 30.0, '2023-01-09 12:00:00', 'Humid'),
       ('Atlanta', 33.7490, -84.3880, 22.0, '2023-01-10 12:00:00', 'Cloudy'),
       ('Boston', 42.3601, -71.0589, 9.0, '2023-01-11 12:00:00', 'Snowy'),
       ('Detroit', 42.3314, -83.0458, 12.0, '2023-01-12 12:00:00', 'Windy'),
       ('Nashville', 36.1627, -86.7816, 24.0, '2023-01-13 12:00:00', 'Sunny'),
       ('Portland', 45.5152, -122.6784, 15.0, '2023-01-14 12:00:00', 'Rainy'),
       ('Las Vegas', 36.1699, -115.1398, 33.0, '2023-01-15 12:00:00', 'Hot'),
       ('Baltimore', 39.2904, -76.6122, 18.0, '2023-01-16 12:00:00', 'Cloudy'),
       ('Philadelphia', 39.9526, -75.1652, 21.0, '2023-01-17 12:00:00', 'Sunny'),
       ('San Diego', 32.7157, -117.1611, 25.0, '2023-01-18 12:00:00', 'Mild'),
       ('Dallas', 32.7767, -96.7970, 34.0, '2023-01-19 12:00:00', 'Sunny'),
       ('San Antonio', 29.4241, -98.4936, 35.0, '2023-01-20 12:00:00', 'Clear'),
       ('Orlando', 28.5383, -81.3792, 29.0, '2023-01-21 12:00:00', 'Rainy'),
       ('Cleveland', 41.4993, -81.6944, 10.0, '2023-01-22 12:00:00', 'Snowy'),
       ('Minneapolis', 44.9778, -93.2650, 8.0, '2023-01-23 12:00:00', 'Blizzard'),
       ('New Orleans', 29.9511, -90.0715, 27.0, '2023-01-24 12:00:00', 'Humid'),
       ('St. Louis', 38.6270, -90.1994, 23.0, '2023-01-25 12:00:00', 'Cloudy'),
       ('Tampa', 27.9506, -82.4572, 31.0, '2023-01-26 12:00:00', 'Sunny'),
       ('Pittsburgh', 40.4406, -79.9959, 14.0, '2023-01-27 12:00:00', 'Rainy'),
       ('Sacramento', 38.5816, -121.4944, 24.0, '2023-01-28 12:00:00', 'Windy'),
       ('Kansas City', 39.0997, -94.5786, 20.0, '2023-01-29 12:00:00', 'Sunny'),
       ('Orlando', 39.0997, -94.5786, 20.0, '2023-01-29 12:00:00', 'Sunny'),
       ('Indianapolis', 39.7684, -86.1581, 19.0, '2023-01-30 12:00:00', 'Cloudy');

