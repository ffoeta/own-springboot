package com.spring.service;

import com.spring.model.City;

import java.util.UUID;

public interface CityService {

    City findByName(String name);

    City save(City city);

    City findById(UUID id);

    void deleteById(UUID id);
}
