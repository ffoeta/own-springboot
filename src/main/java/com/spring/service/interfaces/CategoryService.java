package com.spring.service.interfaces;

import com.spring.model.Category;

import java.util.List;
import java.util.UUID;

public interface CategoryService {

    Category findByName(String name);

    Category save(Category product);

    Category update(Category category);

    List<Category> getAll();

    Category findById(UUID id);

    void deleteById(UUID id);
}
