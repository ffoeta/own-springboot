package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDtoV2 {

    private UUID id;
    private String name;
    private AdminCategoryDtoV2 category;
    private AdminBrandDtoV2 brand;
    private AdminItemDetailsDtoV2 details;

    private Date created;
    private Date updated;
    private Status status;

    public Item to(){

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setCategory(category.to());
        item.setBrand(brand.to());
        item.setDetails(details.to());

        item.setStatus(status);
        item.setUpdated(updated);
        item.setCreated(created);

        return item;
    }

    public static AdminItemDtoV2 from(Item item){
        AdminItemDtoV2 itemDto = new AdminItemDtoV2();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(AdminCategoryDtoV2.from(item.getCategory()));
        itemDto.setBrand(AdminBrandDtoV2.from(item.getBrand()));
        itemDto.setDetails(AdminItemDetailsDtoV2.from(item.getDetails()));
        itemDto.setCreated(item.getCreated());
        itemDto.setUpdated(item.getUpdated());
        itemDto.setStatus(item.getStatus());

        return itemDto;
    }

}
