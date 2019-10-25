package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.City;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminCityDtoV2 {
    private UUID id;
    private String name;


    public static AdminCityDtoV2 from(City city) {

        AdminCityDtoV2 adminCityDtoV2 = new AdminCityDtoV2();

        adminCityDtoV2.id = city.getId();
        adminCityDtoV2.name = city.getName();

        return adminCityDtoV2;
    }

    public City to() {
        City city = new City();

        city.setId(id);
        city.setName(name);

        return city;
    }
}
