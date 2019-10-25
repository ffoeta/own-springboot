package com.spring.rest.V1.open;

import com.spring.dto.V1.open.ByIdRequestDtoV1;
import com.spring.dto.V1.open.item.ItemDtoV1;
import com.spring.model.Item;
import com.spring.service.Implementations.ItemDetailsServiceImpl;
import com.spring.service.Implementations.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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
        List<ItemDtoV1> itemsDto = new ArrayList<>();
        items.forEach(item -> {
            itemsDto.add(ItemDtoV1.fromItem(item));
        });
        return ResponseEntity.ok(itemsDto);
    }

    @GetMapping("item")
    public ResponseEntity geById(ByIdRequestDtoV1 byIdRequestDtoV1) {
        ItemDtoV1 itemDtoV1 = ItemDtoV1.fromItem(itemService.findById(byIdRequestDtoV1.getId()));
        if (itemDtoV1 != null) {
            return ResponseEntity.ok(itemDtoV1);
        }
        return ResponseEntity.noContent().build();
    }
}
