package com.spring.rest.V1;

import com.spring.dto.UserDto;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.Implementations.UserServiceImpl;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/user/")
public class UserRestControllerV1 {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping("/{id}")
    public ResponseEntity me(@RequestHeader("Authorization") String header){
        String token = header.substring(7);
        String username = jwtTokenProvider.getUsername(token);
        HashMap<String, String> response= new HashMap<>();
        response.put("username", username);
        response.put("token", header);

        return ResponseEntity.ok(response);
    }
}
