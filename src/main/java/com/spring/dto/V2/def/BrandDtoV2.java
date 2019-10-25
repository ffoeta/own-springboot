package com.spring.dto.V2.def;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Brand;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BrandDtoV2 {

    @JsonIgnore
    private UUID id;

    private String name;

    public static BrandDtoV2 from(Brand brand) {
        BrandDtoV2 brandDtoV2 = new BrandDtoV2();

        brandDtoV2.id = brand.getId();
        brandDtoV2.name = brand.getName();

        return brandDtoV2;
    }

    public Brand to() {
        Brand brand = new Brand();

        brand.setId(id);
        brand.setName(name);

        return brand;
    }
}
