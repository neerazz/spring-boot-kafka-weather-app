package com.neeraj.poc.kafka.repository;

import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherEntity, Long> {

    Page<WeatherEntity> findByCityContains(PageRequest pageRequest, String city);
}
