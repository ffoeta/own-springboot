package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Category;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminCategoryDtoV2 {

    private UUID id;

    private String name;


    public static AdminCategoryDtoV2 from(Category category) {
        AdminCategoryDtoV2 adminCategoryDtoV2 = new AdminCategoryDtoV2();

        adminCategoryDtoV2.id = category.getId();
        adminCategoryDtoV2.name = category.getName();

        return adminCategoryDtoV2;
    }

    public Category to() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);

        return category;
    }
}
