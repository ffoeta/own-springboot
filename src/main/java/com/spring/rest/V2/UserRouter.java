package com.spring.rest.V2;

import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v2/authorized/user/")
public class UserRouter {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public UserRouter(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    //ITEM

    public ResponseEntity getItem() {return ResponseEntity.ok().build();}

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

