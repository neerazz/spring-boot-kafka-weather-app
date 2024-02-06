package com.neeraj.poc.kafka.converter;

import com.neeraj.poc.kafka.model.pojo.CityDTO;

public class CityConverter {

    public static CityDTO convertToCity(String csvLine) {
//        Remove All Double quotes.
//        csvLine = csvLine.replaceAll("\"", "");
        String[] fields = csvLine.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
        CityDTO cityDTO = new CityDTO();
        try {
            cityDTO.setCity(fields[0].replace("\"", ""));
            cityDTO.setCityAscii(fields[1].replace("\"", ""));
            cityDTO.setLat(parseDoubleOrNull(fields[2]));
            cityDTO.setLng(parseDoubleOrNull(fields[3]));
            cityDTO.setCountry(fields[4].replace("\"", ""));
            cityDTO.setIso2(fields[5].replace("\"", ""));
            cityDTO.setIso3(fields[6].replace("\"", ""));
            cityDTO.setAdminName(fields[7].replace("\"", ""));
            cityDTO.setCapital(fields[8].replace("\"", ""));
            cityDTO.setPopulation(parseDoubleOrNull(fields[9]));
            cityDTO.setId(fields[10].replace("\"", ""));
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse line " + csvLine, e);
        }
        return cityDTO;
    }

    private static Double parseDoubleOrNull(String str) {
        if (str == null || str.trim().isEmpty() || str.replace("\"", "").trim().isEmpty()) {
            return null; // Or return 0.0; depending on how you want to handle empty fields
        }
        return Double.parseDouble(str.replace("\"", ""));
    }

}
