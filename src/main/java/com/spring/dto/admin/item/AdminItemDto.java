package com.spring.dto.admin.item;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import com.spring.model.ItemDetails;
import com.spring.model.Status;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminItemDto {

    private UUID id;
    private String name;
    private AdminCategoryDto category;
    private AdminBrandDto brand;
    private AdminItemDetailsDto details;

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

    public static AdminItemDto from(Item item){
        AdminItemDto itemDto = new AdminItemDto();

        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(AdminCategoryDto.from(item.getCategory()));
        itemDto.setBrand(AdminBrandDto.from(item.getBrand()));
        itemDto.setDetails(AdminItemDetailsDto.from(item.getDetails()));
        itemDto.setCreated(item.getCreated());
        item.setUpdated(item.getUpdated());
        item.setStatus(item.getStatus());

        return itemDto;
    }
}
