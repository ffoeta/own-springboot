package com.spring.rest.V2;

import com.spring.dto.V2.body.OrderBodyV2;
import com.spring.dto.V2.body.UserBodyV2;
import com.spring.dto.V2.def.OrderDtoV2;
import com.spring.dto.V2.def.UserDtoV2;
import com.spring.model.*;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.interfaces.*;
import com.spring.utils.Constants;
import com.spring.utils.Messages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = Constants.USER_ENDPOINT_V2)
public class UserRouter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final CategoryService categoryService;

    private final BrandService brandService;

    private final ItemService itemService;

    private final ItemDetailsService itemDetailsService;

    private final OrderService orderService;

    @Autowired
    public UserRouter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService, CategoryService categoryService, BrandService brandService, ItemService itemService, ItemDetailsService itemDetailsService, OrderService orderService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
        this.categoryService = categoryService;
        this.brandService = brandService;
        this.itemService = itemService;
        this.itemDetailsService = itemDetailsService;
        this.orderService = orderService;
    }

    //ITEM

    @PostMapping("order")
    public ResponseEntity makeOrder(@RequestBody OrderBodyV2 orderBodyV2, @RequestHeader("Authorization") String header) {
        User current = jwtTokenProvider.getUser(header.substring(7));
        Order order = orderService.getOrder(orderBodyV2);

        if ((order != null) || orderBodyV2.getName().isEmpty() || orderBodyV2.getDelivery().isEmpty()){
            return new ResponseEntity<String>(Messages.ENG_ORDER_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }

        order = new Order();
        orderBodyV2.setUser_id(current.getId());
        order = orderService.setOrder(order, orderBodyV2);
        return new ResponseEntity<OrderDtoV2>(OrderDtoV2.from(orderService.make(order)), HttpStatus.OK);
    }


    @PutMapping("update")
    public ResponseEntity updateUser(@RequestBody UserBodyV2 userBodyV2, @RequestHeader("Authorization") String header){
        User user = userService.getUser(userBodyV2);
        if (user == null) {
            return new ResponseEntity<String>(Messages.ENG_USER_BAD_REQUEST, HttpStatus.BAD_REQUEST);
        }
        String current = jwtTokenProvider.getUsername(header.substring(7));
        if (current.equals(user.getUsername())){
            String username = userBodyV2.getUsername();
            String password = userBodyV2.getPassword();
            if (username != null) {
                user.setUsername(username);
            }
            if (password != null) {
                user.setPassword(password);
            }
            return new ResponseEntity<UserDtoV2>(UserDtoV2.fromUser(userService.update(user)), HttpStatus.OK);
        } else {
            return new ResponseEntity<String>(Messages.ENG_USER_NOT_ALLOWED,HttpStatus.FORBIDDEN);
        }
    }

    public ResponseEntity getItems() {return ResponseEntity.ok().build();}

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

