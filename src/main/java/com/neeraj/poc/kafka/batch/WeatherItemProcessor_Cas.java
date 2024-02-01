package com.neeraj.poc.kafka.batch;

import com.neeraj.poc.kafka.domain.WeatherConverter;
import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import com.neeraj.poc.kafka.model.pojo.CityDTO;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;


@Component
public class WeatherItemProcessor_Cas implements ItemProcessor<CityDTO, WeatherEntity_Cass> {


    @Override
    public WeatherEntity_Cass process(CityDTO city) throws Exception {
        return WeatherConverter.getWeatherEntityFromCity(city);
    }

}
