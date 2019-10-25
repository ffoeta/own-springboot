package com.spring.rest.V1.open;

import com.spring.dto.V1.open.UserDtoV1;
import com.spring.model.enums.Status;
import com.spring.model.User;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.Implementations.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rest/")
public class UserRestControllerV1 {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @GetMapping(value = "users")
    public ResponseEntity getUsers() {
        List<User> users = userService.getAll();
        users.removeIf(user -> ((user.getStatus() != Status.ACTIVE)));
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<UserDtoV1> result = new ArrayList<>();
        users.forEach(user -> {
            result.add(UserDtoV1.fromUser(user));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
