package com.neeraj.poc.kafka.repository.cassandra;

import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository_Cass extends CassandraRepository<WeatherEntity_Cass, String> {

    @Query("SELECT * FROM weatherentity_cass WHERE city = ?0")
    Slice<WeatherEntity_Cass> findByCity(PageRequest pageRequest, String city);

    Slice<WeatherEntity_Cass> findByCityAndCountry(PageRequest pageRequest, String city, String country);
    Slice<WeatherEntity_Cass> findByCityAndCountryAndCapital(PageRequest pageRequest, String city, String country, String capital);
}
