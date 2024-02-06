package com.neeraj.poc.kafka.batch;

import com.neeraj.poc.kafka.converter.CityConverter;
import com.neeraj.poc.kafka.model.pojo.CityDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.core.io.ClassPathResource;

@Slf4j
public class WeatherFileReader extends FlatFileItemReader<CityDTO> {

    public WeatherFileReader(String filename) {

        // Set the file resource (path to the file)
        setResource(new ClassPathResource(filename));
        setLineMapper(this::getLineMapper);
        setLinesToSkip(1);
    }

    private CityDTO getLineMapper(String line, int lineNumber) {

        CityDTO cityDTO = null;

        // Create a new CityDTO object and set its fields
        try {
            cityDTO = CityConverter.convertToCity(line);
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse line at number " + lineNumber, e);
        }

        return cityDTO;
    }


}
