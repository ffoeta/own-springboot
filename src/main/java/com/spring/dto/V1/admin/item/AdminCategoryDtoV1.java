package com.spring.dto.V1.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Category;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminCategoryDtoV1 {

    @JsonIgnore
    private UUID id;

    private String name;

    public static AdminCategoryDtoV1 from(Category category) {
        AdminCategoryDtoV1 adminCategoryDtoV1 = new AdminCategoryDtoV1();

        adminCategoryDtoV1.id = category.getId();
        adminCategoryDtoV1.name = category.getName();

        return adminCategoryDtoV1;
    }

    public Category to() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);

        return category;
    }
}