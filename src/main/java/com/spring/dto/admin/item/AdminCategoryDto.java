package com.spring.dto.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Category;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminCategoryDto {

    @JsonIgnore
    private UUID id;

    private String name;

    public static AdminCategoryDto from(Category category) {
        AdminCategoryDto adminCategoryDto = new AdminCategoryDto();

        adminCategoryDto.id = category.getId();
        adminCategoryDto.name = category.getName();

        return adminCategoryDto;
    }

    public Category to() {
        Category category = new Category();

        category.setId(id);
        category.setName(name);

        return category;
    }
}