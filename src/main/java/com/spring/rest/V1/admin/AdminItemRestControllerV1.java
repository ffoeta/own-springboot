package com.spring.rest.V1.admin;

import com.spring.dto.open.ByIdRequestDto;
import com.spring.dto.open.item.ItemDto;
import com.spring.dto.admin.item.AdminItemDto;
import com.spring.model.Brand;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.service.interfaces.BrandService;
import com.spring.service.interfaces.CategoryService;
import com.spring.service.interfaces.ItemDetailsService;
import com.spring.service.interfaces.ItemService;
import com.spring.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminItemRestControllerV1 {

    ItemService itemService;
    ItemDetailsService itemDetailsService;
    CategoryService categoryService;
    BrandService brandService;

    @Autowired
    public AdminItemRestControllerV1(ItemService itemService, ItemDetailsService itemDetailsService, CategoryService categoryService, BrandService brandService) {
        this.itemService = itemService;
        this.itemDetailsService = itemDetailsService;
        this.categoryService = categoryService;
        this.brandService = brandService;
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

    @PostMapping("item")
    public ResponseEntity addItem(@RequestBody ItemDto itemDto) {
        Item item = itemDto.toItem();
        Category category = categoryService.findByName(itemDto.getCategory());
        Brand brand = brandService.findByName(itemDto.getBrand());
        if (category == null) {
            item.setCategory(categoryService.findById(Constants.CATEGORY_NONE));
        } else {
            item.setCategory(category);
        }
        if (brand == null) {
            item.setBrand(brandService.findById(Constants.BRAND_NONE));
        } else {
            item.setBrand(brand);
        }

        return ResponseEntity.ok(AdminItemDto.from(itemService.save(item)));
    }

    @GetMapping("item")
    public ResponseEntity geById(@RequestBody ByIdRequestDto byIdRequestDto) {
        Item item = itemService.findById(byIdRequestDto.getId());
        if (item == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(AdminItemDto.from(item));
    }
}
