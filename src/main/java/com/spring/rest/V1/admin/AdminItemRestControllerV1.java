package com.spring.rest.V1.admin;

import com.spring.dto.V1.open.ByIdRequestDtoV1;
import com.spring.dto.V1.open.item.ItemDtoV1;
import com.spring.dto.V1.admin.item.AdminItemDtoV1;
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
        List<ItemDtoV1> itemsDto = new ArrayList<>();
        items.forEach(item -> {
            itemsDto.add(ItemDtoV1.fromItem(item));
        });
        return ResponseEntity.ok(itemsDto);
    }

    @PostMapping("item")
    public ResponseEntity addItem(@RequestBody ItemDtoV1 itemDtoV1) {
        Item item = itemDtoV1.toItem();
        Category category = categoryService.findByName(itemDtoV1.getCategory());
        Brand brand = brandService.findByName(itemDtoV1.getBrand());
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

        return ResponseEntity.ok(AdminItemDtoV1.from(itemService.save(item)));
    }

    @GetMapping("item")
    public ResponseEntity geById(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        Item item = itemService.findById(byIdRequestDtoV1.getId());
        if (item == null){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(AdminItemDtoV1.from(item));
    }
}
