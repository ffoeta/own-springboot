package com.spring.rest.V1.admin;

import com.spring.dto.V1.admin.user.AdminUserDtoV1;
import com.spring.dto.V1.open.ByIdRequestDtoV1;
import com.spring.model.enums.Status;
import com.spring.model.User;
import com.spring.service.interfaces.ItemService;
import com.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminApiControllerV1 {

    private final UserService userService;
    private final ItemService itemService;

    @Autowired
    public AdminApiControllerV1(UserService userService, ItemService itemService) {
        this.userService = userService;
        this.itemService = itemService;
    }

    @PutMapping("user/invalidate")
    public ResponseEntity invalidateUser(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.NOT_ACTIVE);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }

    @DeleteMapping("user/delete")
    public ResponseEntity deleteUser(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.DELETED);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }

    @PatchMapping("user/restore")
    public ResponseEntity restoreUser(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.ACTIVE);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }

    @PutMapping("item/invalidate")
    public ResponseEntity invalidateItem(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.NOT_ACTIVE);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }

    @DeleteMapping("item/delete")
    public ResponseEntity deleteItem(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.DELETED);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }

    @PatchMapping("item/restore")
    public ResponseEntity restoreItem(@RequestBody ByIdRequestDtoV1 byIdRequestDtoV1) {
        User user = userService.findById(byIdRequestDtoV1.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.ACTIVE);

        return ResponseEntity.ok(AdminUserDtoV1.fromUser(userService.update(user)));
    }
}
