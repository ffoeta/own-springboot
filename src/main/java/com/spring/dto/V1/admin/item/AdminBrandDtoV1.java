package com.spring.dto.V1.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Brand;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminBrandDtoV1 {

    @JsonIgnore
    private UUID id;

    private String name;

    public static AdminBrandDtoV1 from(Brand brand) {
        AdminBrandDtoV1 adminBrandDtoV1 = new AdminBrandDtoV1();

        adminBrandDtoV1.id = brand.getId();
        adminBrandDtoV1.name = brand.getName();

        return adminBrandDtoV1;
    }

    public Brand to() {
        Brand category = new Brand();

        category.setId(id);
        category.setName(name);

        return category;
    }
}