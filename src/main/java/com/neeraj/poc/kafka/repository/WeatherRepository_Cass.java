package com.neeraj.poc.kafka.repository;

import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface WeatherRepository_Cass extends CassandraRepository<WeatherEntity_Cass, String> {

    Page<WeatherEntity_Cass> findByCityContains(PageRequest pageRequest, String city);
}
