package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Category;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonCategoryDtoV2 {

    @JsonIgnore
    private UUID id;

    private String name;


    public static AnonCategoryDtoV2 from(Category category) {
        AnonCategoryDtoV2 anonCategoryDtoV2 = new AnonCategoryDtoV2();

        anonCategoryDtoV2.id = category.getId();
        anonCategoryDtoV2.name = category.getName();

        return anonCategoryDtoV2;
    }

    public Category to() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);

        return category;
    }
}
