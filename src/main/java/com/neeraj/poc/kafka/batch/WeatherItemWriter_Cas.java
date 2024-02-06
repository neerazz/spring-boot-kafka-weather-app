package com.neeraj.poc.kafka.batch;

import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import com.neeraj.poc.kafka.repository.cassandra.WeatherRepository_Cass;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class WeatherItemWriter_Cas implements ItemWriter<WeatherEntity_Cass> {

    WeatherRepository_Cass weatherRepositoryCass;

    @Override
    public void write(Chunk<? extends WeatherEntity_Cass> chunk) throws Exception {
        log.debug("Writing {} Items to Cassandra", chunk.size());
        chunk.forEach(weatherRepositoryCass::save);
    }
}
