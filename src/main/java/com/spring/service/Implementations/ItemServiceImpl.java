package com.spring.service.Implementations;

import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.model.Brand;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.model.ItemDetails;
import com.spring.model.enums.Status;
import com.spring.repository.BrandRepository;
import com.spring.repository.CategoryRepository;
import com.spring.repository.ItemDetailsRepository;
import com.spring.repository.ItemRepository;
import com.spring.service.interfaces.ItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemDetailsRepository itemDetailsRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository, ItemDetailsRepository itemDetailsRepository, CategoryRepository categoryRepository, BrandRepository brandRepository) {
        this.itemRepository = itemRepository;
        this.itemDetailsRepository = itemDetailsRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public Item save(Item item){

        ItemDetails itemDetails = new ItemDetails();

        item.setId(UUID.randomUUID());
        item.setStatus(Status.ACTIVE);

        itemDetails.setId(UUID.randomUUID());
        itemDetails.setStatus(Status.ACTIVE);

        Date date = new Date();

        item.setCreated(date);
        item.setUpdated(date);

        itemDetails.setCreated(date);
        itemDetails.setUpdated(date);

        itemDetailsRepository.save(itemDetails);
        itemRepository.save(item);

        Item itemTmp = itemRepository.findById(item.getId()).orElse(null);
        ItemDetails itemDetailsTmp = itemDetailsRepository.findById(itemDetails.getId()).orElse(null);

        itemTmp.setDetails(itemDetails);
        itemDetailsTmp.setItem(item);

        itemDetailsRepository.save(itemDetails);

        return itemRepository.save(itemTmp);
    }

    @Override
    public Item update(Item item) {
        item.setUpdated(new Date());
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

    public Item getItem(ItemBodyV2 itemBodyV2) {
        Item item = null;
        if (itemBodyV2.getId() != null) {
            item = findById(UUID.fromString(itemBodyV2.getId()));
        } else {
            item = findByName(itemBodyV2.getName());
        }
        return item;
    }

    public Item setItem(Item item, ItemBodyV2 itemBodyV2) {
        if (item == null) {
            return null;
        }

        UUID id = UUID.fromString(itemBodyV2.getId());
        String name = itemBodyV2.getName();
        Category category = categoryRepository.findByName(itemBodyV2.getCategoty());
        Brand brand = brandRepository.findByName(itemBodyV2.getBrand());

        if (id != null) {
            item.setId(id);
        }
        if (name != null) {
            item.setName(name);
        }
        if (category != null) {
            item.setCategory(category);
        }
        if (brand != null) {
            item.setBrand(brand);
        }
        return item;
    }
}
