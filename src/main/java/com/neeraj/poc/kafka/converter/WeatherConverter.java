package com.neeraj.poc.kafka.converter;

import com.neeraj.poc.kafka.model.entity.WeatherEntity;
import com.neeraj.poc.kafka.model.entity.WeatherEntity_Cass;
import com.neeraj.poc.kafka.model.pojo.CityDTO;
import com.neeraj.poc.kafka.model.pojo.WeatherDTO;
import org.modelmapper.ModelMapper;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class WeatherConverter {

    private static final ModelMapper mapper = new ModelMapper();

    public static List<WeatherDTO> getWeatherDTOFromCassEntity(List<WeatherEntity_Cass> weatherEntities) {
        if (weatherEntities == null) {
            return new ArrayList<>();
        } else {
            return weatherEntities.stream()
                    .map(WeatherConverter::getWeatherDTO)
                    .toList();
        }
    }

    public static List<WeatherDTO> getWeatherDTO(List<WeatherEntity> weatherEntities) {
        if (weatherEntities == null) {
            return new ArrayList<>();
        } else {
            return weatherEntities.stream()
                    .map(entity -> mapper.map(entity, WeatherDTO.class))
                    .toList();
        }
    }

    public static WeatherDTO getWeatherDTO(WeatherEntity_Cass city) {
//        return WeatherDTO.builder()
//                .longitude(city.getLongitude())
//                .longitude(city.getLongitude())
//                .build();
        return mapper.map(city, WeatherDTO.class);
    }

    public static WeatherEntity_Cass getWeatherCassEntityFromDTO(WeatherDTO city) {
        var entity = mapper.map(city, WeatherEntity_Cass.class);
        entity.setId(UUID.randomUUID().toString());
        return entity;
    }

    public static WeatherEntity_Cass getWeatherEntityFromCity(CityDTO city) {
        Random ran = new Random();
        var weatherInCelsius = ran.nextDouble(-50, 51);
        return WeatherEntity_Cass.builder()
                .id(city.getId())
                .city(city.getCity())
                .capital(city.getCapital())
                .country(city.getCountry())
                .population(city.getPopulation())
                .longitude(city.getLng())
                .latitude(city.getLat())

                .timestamp(LocalDateTime.now().minusDays(ran.nextInt(0, 120)))
                .weatherInCelsius(weatherInCelsius)
                .description(getWeatherDescription(weatherInCelsius))

                .build();
    }

    /**
     * @param weatherInCelsius
     * @return String
     * Temperatures from -50 to 15 degrees Celsius are categorized as "Cold".
     * Temperatures from 16 to 40 degrees Celsius are categorized as "Moderate".
     * Temperatures from 41 to 60 degrees Celsius are categorized as "Hot".
     * Any other temperatures are categorized as "Extreme".
     */
    private static String getWeatherDescription(double weatherInCelsius) {
        return switch ((int) weatherInCelsius) {
            case -50, -49, -48, -47, -46, -45, -44, -43, -42, -41, -40, -39, -38, -37, -36, -35, -34, -33, -32, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -21, -20, -19, -18, -17, -16, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15 ->
                    "Cold";
            case 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 ->
                    "Moderate";
            case 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 58, 59, 60 -> "Hot";
            default -> "Extreme";
        };
    }

    public static WeatherEntity getWeatherEntityFromDTO(WeatherDTO weatherDTO) {
//        return WeatherEntity.builder()
//                .longitude(weatherDTO.getLongitude())
//                .latitude(weatherDTO.getLatitude())
//                .timestamp(weatherDTO.getTimestamp())
//                .description(weatherDTO.getDescription())
//                .city(weatherDTO.getCity())
//                .weatherInCelsius(weatherDTO.getWeatherInCelsius())
//                .build();
        return mapper.map(weatherDTO, WeatherEntity.class);
    }
}
