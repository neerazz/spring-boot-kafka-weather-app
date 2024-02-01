package com.neeraj.poc.kafka.model.pojo;

import lombok.Data;

@Data
public class CityDTO {

    private String city;
    private String cityAscii;
    private Double lat;
    private Double lng;
    private Double population;

    private String country;
    private String iso2;
    private String iso3;
    private String adminName;
    private String capital;
    private String id;

}
