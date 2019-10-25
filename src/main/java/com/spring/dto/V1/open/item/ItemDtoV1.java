package com.spring.dto.V1.open.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDtoV1 {

    private String name;
    private String category;
    private String brand;

    public Item toItem(){

        Item item = new Item();
        item.setName(name);

        return item;
    }

    public static ItemDtoV1 fromItem(Item item){
        ItemDtoV1 itemDtoV1 = new ItemDtoV1();

        itemDtoV1.setName(item.getName());
        itemDtoV1.setCategory(item.getCategory().getName());
        itemDtoV1.setBrand(item.getBrand().getName());

        return itemDtoV1;
    }
}
