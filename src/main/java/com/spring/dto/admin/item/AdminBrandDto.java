package com.spring.dto.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Brand;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminBrandDto {

    @JsonIgnore
    private UUID id;

    private String name;

    public static AdminBrandDto from(Brand brand) {
        AdminBrandDto adminBrandDto = new AdminBrandDto();

        adminBrandDto.id = brand.getId();
        adminBrandDto.name = brand.getName();

        return adminBrandDto;
    }

    public Brand to() {
        Brand category = new Brand();

        category.setId(id);
        category.setName(name);

        return category;
    }
}