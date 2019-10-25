package com.spring.dto.V2.anon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Brand;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonBrandDtoV2 {

    @JsonIgnore
    private UUID id;

    private String name;

    public static AnonBrandDtoV2 from(Brand brand) {
        AnonBrandDtoV2 anonBrandDtoV2 = new AnonBrandDtoV2();

        anonBrandDtoV2.id = brand.getId();
        anonBrandDtoV2.name = brand.getName();

        return anonBrandDtoV2;
    }

    public Brand to() {
        Brand brand = new Brand();

        brand.setId(id);
        brand.setName(name);

        return brand;
    }
}
