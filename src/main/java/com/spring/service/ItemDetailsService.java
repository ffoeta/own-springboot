package com.spring.service;

import com.spring.model.Item;
import com.spring.model.ItemDetails;

import java.util.UUID;

public interface ItemDetailsService {
    ItemDetails save(ItemDetails itemDetails, Item item);

    ItemDetails findById(UUID id);

    void deleteById(UUID id);
}
