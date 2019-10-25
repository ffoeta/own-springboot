package com.spring.dto.V2.def;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.dto.V2.admin.CategoryDtoV2;
import com.spring.model.Item;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDtoV2 {

    private UUID id;
    private String name;
    private CategoryDtoV2 category;
    private BrandDtoV2 brand;
    private ItemDetailsDtoV2 details;

    public Item to(){

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setCategory(category.to());
        item.setBrand(brand.to());
        item.setDetails(details.to());

        return item;
    }

    public static ItemDtoV2 from(Item item){
        ItemDtoV2 itemDto = new ItemDtoV2();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(CategoryDtoV2.from(item.getCategory()));
        itemDto.setBrand(BrandDtoV2.from(item.getBrand()));
        itemDto.setDetails(ItemDetailsDtoV2.from(item.getDetails()));

        return itemDto;
    }

}
