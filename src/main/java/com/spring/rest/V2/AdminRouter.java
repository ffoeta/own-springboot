package com.spring.rest.V2;

import com.spring.dto.V2.admin.AdminCategoryDtoV2;
import com.spring.dto.V2.admin.AdminItemDtoV2;
import com.spring.dto.V2.body.CategoryBodyV2;
import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.model.Brand;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.model.enums.Status;
import com.spring.dto.responses.PrettyResponse;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.interfaces.*;
import com.spring.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(value = "/api/v2/authorized/head/")

public class AdminRouter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ItemService itemService;

    private final ItemDetailsService itemDetailsService;

    @Autowired
    public AdminRouter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, CategoryService categoryService, BrandService brandService, ItemService itemService, ItemDetailsService itemDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.itemService = itemService;
        this.itemDetailsService = itemDetailsService;
    }

    //ITEM

    public Item getItem(ItemBodyV2 itemBodyV2) {
        Item item;
        if (itemBodyV2.getId() != null) {
            item = itemService.findById(UUID.fromString(itemBodyV2.getId()));
        } else {
            item = itemService.findByName(itemBodyV2.getName());
        }
        return item;
    }

    public Item setItem(Item item, ItemBodyV2 itemBodyV2) {
        if (item == null) {
            return null;
        }

        UUID id = UUID.fromString(itemBodyV2.getId());
        String name = itemBodyV2.getName();
        Category category = categoryService.findByName(itemBodyV2.getCategoty());
        Brand brand = brandService.findByName(itemBodyV2.getBrand());

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

    @GetMapping("item/select")
    public ResponseEntity selectItem(@RequestBody ItemBodyV2 itemBodyV2) {
        Item item = getItem(itemBodyV2);
        if (item == null) {
            return new ResponseEntity<String>(Messages.ENG_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(AdminItemDtoV2.from(item)), HttpStatus.FOUND);
    }

    @GetMapping("items/select")
    public ResponseEntity selectAllItems() {
        List<Item> items = itemService.getAll();
        if (items.isEmpty()){
            return new ResponseEntity<String>(Messages.ENG_ITEM_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        List<AdminItemDtoV2> adminItemDtoV2List = new ArrayList<>();
        items.forEach(item -> {
            adminItemDtoV2List.add(AdminItemDtoV2.from(item));
        });
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(adminItemDtoV2List), HttpStatus.FOUND);
    }

    public ResponseEntity searchItems() {
        //TODO
        return ResponseEntity.ok().build();
    }

    public ResponseEntity loadItems() {
        //TODO
        return ResponseEntity.ok().build();
    }

    @PostMapping("item/insert")
    public ResponseEntity insertItem(@RequestBody ItemBodyV2 itemBodyV2){
        Item item = getItem(itemBodyV2);
        if (item != null) {
            return new ResponseEntity<String>(Messages.ENG_ITEM_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        item = new Item();
        item = setItem(item, itemBodyV2);

        if (item == null){
            return ResponseEntity.badRequest().build();
        }
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(AdminItemDtoV2.from(itemService.save(item))), HttpStatus.FOUND);
    }

    @PutMapping("item/update")
    public ResponseEntity updateItem(@RequestBody ItemBodyV2 itemBodyV2) {
        Item item = getItem(itemBodyV2);
        if (item == null) {
            return new ResponseEntity<String>(Messages.ENG_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        item = setItem(item, itemBodyV2);

        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(AdminItemDtoV2.from(itemService.update(item))), HttpStatus.FOUND);
    }

    @PutMapping("item/invalidate")
    public ResponseEntity invalidateItem(@RequestBody ItemBodyV2 itemBodyV2) {
        Item item = getItem(itemBodyV2);
        if (item == null){
            return new ResponseEntity<String>(Messages.ENG_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        item.setStatus(Status.NOT_ACTIVE);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(AdminItemDtoV2.from(itemService.update(item))), HttpStatus.FOUND);
    }

    @PutMapping("item/restore")
    public ResponseEntity restoreItem(@RequestBody ItemBodyV2 itemBodyV2) {
        Item item = getItem(itemBodyV2);
        if (item == null){
            return new ResponseEntity<String>(Messages.ENG_ITEM_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        item.setStatus(Status.ACTIVE);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminItemDtoV2>(AdminItemDtoV2.from(itemService.update(item))), HttpStatus.FOUND);
    }

    //CATEGORY

    public Category getCategory(CategoryBodyV2 categoryBodyV2) {
        Category category;
        if (categoryBodyV2.getId() != null) {
            category = categoryService.findById(UUID.fromString(categoryBodyV2.getId()));
        } else {
            category = categoryService.findByName(categoryBodyV2.getName());
        }
        if (category == null) {
            return null;
        }
        return category;
    }

    public Category setCategory(Category category, CategoryBodyV2 categoryBodyV2) {
        if (category == null) {
            return null;
        }
        UUID id = UUID.fromString(categoryBodyV2.getId());
        String name = categoryBodyV2.getName();
        if (id != null) {
            category.setId(id);
        }
        if (name != null) {
            category.setName(name);
        }
        return category;
    }

    @GetMapping("category/select")
    public ResponseEntity selectCategory(@RequestBody CategoryBodyV2 categoryBodyV2) {
        Category category = getCategory(categoryBodyV2);
        if (category == null){
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(AdminCategoryDtoV2.from(category)), HttpStatus.FOUND);
    }

    @GetMapping("categories/select")
    public ResponseEntity selectCategories() {
        List<Category> categories = categoryService.getAll();
        if (categories.isEmpty()){
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        List<AdminCategoryDtoV2> categoryDtoV2s = new ArrayList<>();
        categories.forEach(category -> {
            categoryDtoV2s.add(AdminCategoryDtoV2.from(category));
        });
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(categoryDtoV2s), HttpStatus.FOUND);
    }

    @PostMapping("category/insert")
    public ResponseEntity insertCategory(@RequestBody CategoryBodyV2 categoryBodyV2) {
        Category category = getCategory(categoryBodyV2);
        if (category != null) {
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
        category = setCategory(category, categoryBodyV2);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(AdminCategoryDtoV2.from(categoryService.save(category))), HttpStatus.CREATED);
    }

    public ResponseEntity searchCategory() {
        //TODO
        return ResponseEntity.ok().build();
    }

    @PutMapping("category/update")
    public ResponseEntity updateCategory(@RequestBody CategoryBodyV2 categoryBodyV2) {
        Category category = getCategory(categoryBodyV2);
        if (category == null) {
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        category = setCategory(category, categoryBodyV2);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(AdminCategoryDtoV2.from(categoryService.update(category))), HttpStatus.FOUND);
    }

    @PutMapping("category/invalidate")
    public ResponseEntity invalidateCategory(@RequestBody CategoryBodyV2 categoryBodyV2) {
        Category category = getCategory(categoryBodyV2);
        if (category == null){
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        category.setStatus(Status.NOT_ACTIVE);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(AdminCategoryDtoV2.from(categoryService.update(category))), HttpStatus.FOUND);
    }

    @PutMapping("category/restore")
    public ResponseEntity restoreCategory(@RequestBody CategoryBodyV2 categoryBodyV2) {
        Category category = getCategory(categoryBodyV2);
        if (category == null){
            return new ResponseEntity<String>(Messages.ENG_CATEGORY_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
        category.setStatus(Status.ACTIVE);
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<AdminCategoryDtoV2>(AdminCategoryDtoV2.from(categoryService.update(category))), HttpStatus.FOUND);
    }
    //BRAND

    public ResponseEntity addBrand() {return ResponseEntity.ok().build();}

    public ResponseEntity getBrands() {return ResponseEntity.ok().build();}

    public ResponseEntity searchBrand() {return ResponseEntity.ok().build();}

    public ResponseEntity updateBrand() {return ResponseEntity.ok().build();}

    public ResponseEntity invalidateBrand() {return ResponseEntity.ok().build();}

    //DETAILS

    public ResponseEntity getDetails() {return ResponseEntity.ok().build();}

    public ResponseEntity updateDetails() {return ResponseEntity.ok().build();}

    public ResponseEntity invalidateDetails() {return ResponseEntity.ok().build();}

    //ORDER

    public ResponseEntity getUserOrders() {return ResponseEntity.ok().build();}

    public ResponseEntity cancelOrder() {return ResponseEntity.ok().build();}

    public ResponseEntity updateOrder() {return ResponseEntity.ok().build();}

    public ResponseEntity invalidateOrder() {return ResponseEntity.ok().build();}

    //USER

    public ResponseEntity deactivateUser() {return ResponseEntity.ok().build();}

    public ResponseEntity restoreUser() {return ResponseEntity.ok().build();}

    public ResponseEntity invalidateUser() {return ResponseEntity.ok().build();}

    public ResponseEntity grantAccess() {return ResponseEntity.ok().build();}
}

