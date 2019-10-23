package com.spring.service.Implementations;

import com.spring.model.*;
import com.spring.repository.*;
import com.spring.service.ItemDetailsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class ItemDetailsServiceImpl implements ItemDetailsService {


    private final ItemDetailsRepository itemDetailsRepository;
    private final ItemRepository itemRepository;
    private final ProducerRepository producerRepository;

    @Autowired
    public ItemDetailsServiceImpl(ItemDetailsRepository itemDetailsRepository, ItemRepository itemRepository, ProducerRepository producerRepository, BrandRepository brandRepository, CategoryRepository categoryRepository) {
        this.itemDetailsRepository = itemDetailsRepository;
        this.itemRepository = itemRepository;
        this.producerRepository = producerRepository;
    }

    @Override
    public ItemDetails save(ItemDetails itemDetails, Item item) {
        Item itemTmp = itemRepository.findById(item.getId()).orElse(null);
        Producer producerTmp = producerRepository.findByName(itemDetails.getProducer().getName());
        if ((itemTmp == null) || (item.getId() == null) || (producerTmp.getId() == null)) {
            return null;
        }

        itemDetails.setId(UUID.randomUUID());
        itemDetails.setItem(item);

        Date date = new Date();
        itemDetails.setCreated(date);
        itemDetails.setUpdated(date);

        itemDetails.setStatus(Status.ACTIVE);

        return itemDetailsRepository.save(itemDetails);
    }

    @Override
    public ItemDetails findById(UUID id) {
        return itemDetailsRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(UUID id) {
        itemDetailsRepository.deleteById(id);
    }
}
