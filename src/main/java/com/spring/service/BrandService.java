package com.spring.service;

import com.spring.model.Brand;

import java.util.UUID;

public interface BrandService {

    Brand findByName(String name);

    Brand save(Brand brand);

    Brand findById(UUID id);

    void deleteById(UUID id);
}
