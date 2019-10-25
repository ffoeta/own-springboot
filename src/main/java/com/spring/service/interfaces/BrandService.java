package com.spring.service.interfaces;

import com.spring.model.Brand;

import java.util.List;
import java.util.UUID;

public interface BrandService {

    Brand findByName(String name);

    Brand save(Brand brand);

    Brand update(Brand brand);

    List<Brand> getAll();

    Brand findById(UUID id);

    void deleteById(UUID id);
}
