package com.spring.dto.open.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {

    private String name;
    private String category;
    private String brand;

    public Item toItem(){

        Item item = new Item();
        item.setName(name);

        return item;
    }

    public static ItemDto fromItem(Item item){
        ItemDto itemDto = new ItemDto();

        itemDto.setName(item.getName());
        itemDto.setCategory(item.getCategory().getName());
        itemDto.setBrand(item.getBrand().getName());

        return itemDto;
    }
}
