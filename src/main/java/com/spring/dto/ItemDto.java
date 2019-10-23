package com.spring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import com.spring.service.BrandService;
import com.spring.service.CategoryService;
import com.spring.service.ItemDetailsService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemDto {

    @Autowired
    @JsonIgnore
    private CategoryService categoryService;

    @Autowired
    @JsonIgnore
    private BrandService brandService;

    @Autowired
    @JsonIgnore
    protected ItemDetailsService itemDetailsService;



    private UUID id;
    private String name;
    private UUID details;
    private String category;
    private String brand;

    public Item toItem(){
        Item item = new Item();
        item.setId(UUID.randomUUID());
        item.setName(name);
        item.setCategory(categoryService.findByName(category));
        item.setBrand(brandService.findByName(brand));
        item.setDetails(itemDetailsService.findById(details));

        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);

        return item;
    }

    public static ItemDto fromItem(Item item){
        ItemDto itemDto = new ItemDto();
        itemDto.setId(item.getId());
        itemDto.setName(item.getName());
        itemDto.setCategory(item.getCategory().getName());
        itemDto.setBrand(item.getBrand().getName());
        itemDto.setDetails(item.getDetails().getId());

        return itemDto;
    }
}
