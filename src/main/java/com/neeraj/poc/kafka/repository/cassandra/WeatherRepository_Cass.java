package com.neeraj.poc.kafka.repository.cassandra;

import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository_Cass extends CassandraRepository<WeatherEntity_Cass, String> {

    Slice<WeatherEntity_Cass> findByCityContains(PageRequest pageRequest, String city);
}
