package com.spring.rest.V1.admin;

import com.spring.dto.V1.admin.user.AdminUserDtoV1;
import com.spring.dto.V1.open.ByIdRequestDtoV1;
import com.spring.model.User;
import com.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * REST controller for ROLE_ADMIN requests.
 *
 * @author Eugene Suleimanov
 * @version 1.0
 */

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminUserRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminUserRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "user")
    public ResponseEntity<AdminUserDtoV1> getUserById(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        UUID id = byIdRequestDtoV1.getId();
        System.out.println(id);
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<AdminUserDtoV1>(AdminUserDtoV1.fromUser(user), HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public ResponseEntity getUsers() {
        List<User> users = userService.getAll();

        List<AdminUserDtoV1> result = new ArrayList<>();

        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        users.forEach(user -> {
            result.add(AdminUserDtoV1.fromUser(user));
        });

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
