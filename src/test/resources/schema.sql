DROP TABLE IF EXISTS WEATHER_ENTITY;

CREATE TABLE WEATHER_ENTITY (
                                id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                city VARCHAR(255),
                                temperature DOUBLE,
                                humidity DOUBLE,
                                description VARCHAR(255)
);
