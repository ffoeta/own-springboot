package com.spring.dto.V2.anon;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.dto.V2.admin.AnonCategoryDtoV2;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonItemDtoV2 {

    private UUID id;
    private String name;
    private AnonCategoryDtoV2 category;
    private AnonBrandDtoV2 brand;
    private AnonItemDetailsDtoV2 details;

    public Item to(){

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setCategory(category.to());
        item.setBrand(brand.to());
        item.setDetails(details.to());

        return item;
    }

    public static AnonItemDtoV2 from(Item item){
        AnonItemDtoV2 itemDto = new AnonItemDtoV2();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(AnonCategoryDtoV2.from(item.getCategory()));
        itemDto.setBrand(AnonBrandDtoV2.from(item.getBrand()));

        return itemDto;
    }

}
