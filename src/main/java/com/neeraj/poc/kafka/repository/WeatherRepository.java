package com.neeraj.poc.kafka.repository;

import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {
}
