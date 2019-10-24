package com.spring.service.Implementations;

import com.spring.model.Item;
import com.spring.model.ItemDetails;
import com.spring.model.Status;
import com.spring.repository.ItemDetailsRepository;
import com.spring.repository.ItemRepository;
import com.spring.service.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemDetailsRepository itemDetailsRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemDetailsRepository itemDetailsRepository) {
        this.itemRepository = itemRepository;
        this.itemDetailsRepository = itemDetailsRepository;
    }

    @Override
    public Item save(Item item) {

        item.setId(UUID.randomUUID());
        item.setStatus(Status.ACTIVE);

        Date date = new Date();
        item.setCreated(date);
        item.setUpdated(date);

        return itemRepository.save(item);
    }

    @Override
    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    @Override
    public Item findById(UUID id) {
        return itemRepository.findById(id).orElse(null);
    }

    @Override
    public List<Item> getAll() {
        return itemRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        itemRepository.deleteById(id);
    }
}