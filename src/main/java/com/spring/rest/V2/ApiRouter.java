package com.spring.rest.V2;

import com.spring.dto.V2.def.ItemDtoV2;
import com.spring.dto.V2.body.ItemBodyV2;
import com.spring.dto.responses.PrettyResponse;
import com.spring.model.Brand;
import com.spring.model.Category;
import com.spring.model.Item;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.interfaces.*;
import com.spring.utils.Constants;
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
@RequestMapping(value = Constants.API_ENDPOINT_V2)
public class ApiRouter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ItemService itemService;

    private final ItemDetailsService itemDetailsService;

    @Autowired
    public ApiRouter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, CategoryService categoryService, BrandService brandService, ItemService itemService, ItemDetailsService itemDetailsService) {
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
                new PrettyResponse<ItemDtoV2>(ItemDtoV2.from(item)), HttpStatus.FOUND);
    }

    @GetMapping("items/select")
    public ResponseEntity selectAllItems() {
        List<Item> items = itemService.getAll();
        if (items.isEmpty()){
            return new ResponseEntity<String>(Messages.ENG_ITEM_NO_CONTENT, HttpStatus.NO_CONTENT);
        }
        List<ItemDtoV2> itemDtoV2List = new ArrayList<>();
        items.forEach(item -> {
            itemDtoV2List.add(ItemDtoV2.from(item));
        });
        return new ResponseEntity<PrettyResponse>(
                new PrettyResponse<ItemDtoV2>(itemDtoV2List), HttpStatus.FOUND);
    }


    public ResponseEntity serachItems() {return ResponseEntity.ok().build();}


    //CATEGORY

    public ResponseEntity getCategories() {return ResponseEntity.ok().build();}

    public ResponseEntity searchCategory() {return ResponseEntity.ok().build();}

    //BRAND

    public ResponseEntity getBrands() {return ResponseEntity.ok().build();}

    public ResponseEntity searchBrand() {return ResponseEntity.ok().build();}

    //DETAILS

    public ResponseEntity getDetails() {return ResponseEntity.ok().build();}

}

