package com.spring.dto.V1.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDtoV1 {

    private UUID id;
    private String name;
    private AdminCategoryDtoV1 category;
    private AdminBrandDtoV1 brand;
    private AdminItemDetailsDtoV1 details;

    private Date created;
    private Date updated;
    private Status status;

    public Item to(){

        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setStatus(status);
        item.setUpdated(updated);
        item.setCreated(created);

        return item;
    }

    public static AdminItemDtoV1 from(Item item){
        AdminItemDtoV1 itemDto = new AdminItemDtoV1();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(AdminCategoryDtoV1.from(item.getCategory()));
        itemDto.setBrand(AdminBrandDtoV1.from(item.getBrand()));
        itemDto.setDetails(AdminItemDetailsDtoV1.from(item.getDetails()));
        itemDto.setCreated(item.getCreated());
        item.setUpdated(item.getUpdated());
        item.setStatus(item.getStatus());

        return itemDto;
    }
}
