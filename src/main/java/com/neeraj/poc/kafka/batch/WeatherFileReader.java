package com.neeraj.poc.kafka.batch;

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

    }

    private CityDTO getLineMapper(String line, int lineNumber) {
        // Assuming the file is a CSV and fields are separated by commas
        String[] fields = line.split(",");

        // Create a new CityDTO object and set its fields
        CityDTO cityDTO = new CityDTO();
        try {
            cityDTO.setCity(fields[0]);
            cityDTO.setCityAscii(fields[1]);
            cityDTO.setLat(Double.parseDouble(fields[2]));
            cityDTO.setLng(Double.parseDouble(fields[3]));
            cityDTO.setPopulation(Double.parseDouble(fields[4]));
            cityDTO.setCountry(fields[5]);
            cityDTO.setIso2(fields[6]);
            cityDTO.setIso3(fields[7]);
            cityDTO.setAdminName(fields[8]);
            cityDTO.setCapital(fields[9]);
            cityDTO.setId(fields[10]);
            // Add any additional field parsing as necessary
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse line at number " + lineNumber, e);
        }

        return cityDTO;
    }


}
