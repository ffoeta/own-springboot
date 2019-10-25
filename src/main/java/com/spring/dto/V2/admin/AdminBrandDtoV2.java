package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Brand;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminBrandDtoV2 {

    private UUID id;
    private String name;

    public static AdminBrandDtoV2 from(Brand brand) {
        AdminBrandDtoV2 adminBrandDtoV2 = new AdminBrandDtoV2();

        adminBrandDtoV2.id = brand.getId();
        adminBrandDtoV2.name = brand.getName();

        return adminBrandDtoV2;
    }

    public Brand to() {
        Brand brand = new Brand();

        brand.setId(id);
        brand.setName(name);

        return brand;
    }
}
