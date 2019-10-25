package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Category;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CategoryDtoV2 {

    @JsonIgnore
    private UUID id;

    private String name;


    public static CategoryDtoV2 from(Category category) {
        CategoryDtoV2 categoryDtoV2 = new CategoryDtoV2();

        categoryDtoV2.id = category.getId();
        categoryDtoV2.name = category.getName();

        return categoryDtoV2;
    }

    public Category to() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);

        return category;
    }
}
