package com.spring.rest.V1;

import com.spring.dto.ByIdRequestDto;
import com.spring.dto.ItemDto;
import com.spring.model.Item;
import com.spring.service.Implementations.ItemDetailsServiceImpl;
import com.spring.service.Implementations.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/api/v1/rest/")
public class ItemRestControllerV1 {

    ItemServiceImpl itemService;
    ItemDetailsServiceImpl itemDetailsService;

    @Autowired
    public ItemRestControllerV1(ItemServiceImpl itemService, ItemDetailsServiceImpl itemDetailsService) {
        this.itemService = itemService;
        this.itemDetailsService = itemDetailsService;
    }

    @GetMapping("items")
    public ResponseEntity getItems() {
        List<Item> items = itemService.getAll();
        System.out.println(items.size());
        List<ItemDto> itemsDto = new ArrayList<>();
        items.forEach(item -> {
            itemsDto.add(ItemDto.fromItem(item));
        });
        return ResponseEntity.ok(itemsDto);
    }

    @GetMapping("item")
    public ResponseEntity geById(ByIdRequestDto byIdRequestDto) {
        List<Item> items = itemService.getAll();
        System.out.println(items.size());
        List<ItemDto> itemsDto = new ArrayList<>();
        items.forEach(item -> {
            itemsDto.add(ItemDto.fromItem(item));
        });
        return ResponseEntity.ok(itemsDto);
    }
}
