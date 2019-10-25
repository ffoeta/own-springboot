package com.spring.service.interfaces;

import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.model.Item;

import java.util.List;
import java.util.UUID;

public interface ItemService {
    Item save(Item item);

    Item update(Item item);

    Item findByName(String name);

    Item findById(UUID id);

    List<Item> getAll();

    void deleteById(UUID id);

    Item getItem(ItemBodyV2 itemBodyV2);

    Item setItem(Item item, ItemBodyV2 itemBodyV2);
}
