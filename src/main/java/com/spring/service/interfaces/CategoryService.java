package com.spring.service.interfaces;

import com.spring.model.Category;

import java.util.UUID;

public interface CategoryService {

    Category findByName(String name);

    Category save(Category product);

    Category update(Category category);

    Category findById(UUID id);

    void deleteById(UUID id);
}
