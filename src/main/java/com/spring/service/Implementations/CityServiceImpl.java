package com.spring.service.Implementations;

import com.spring.model.City;
import com.spring.repository.CategoryRepository;
import com.spring.repository.CityRepository;
import com.spring.service.interfaces.CityService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class CityServiceImpl implements CityService {

    CityRepository cityRepository;

    @Autowired
    public CityServiceImpl(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public City findByName(String name) {
        return cityRepository.findByName(name);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City update(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findById(UUID id) {
        return cityRepository.findById(id).orElse(null);
    }

    @Override
    public List<City> getAll() {
        return cityRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        cityRepository.deleteById(id);
    }
}
