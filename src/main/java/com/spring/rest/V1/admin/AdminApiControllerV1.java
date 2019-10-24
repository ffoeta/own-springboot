package com.spring.rest.V1.admin;

import com.spring.dto.admin.user.AdminUserDto;
import com.spring.dto.open.ByIdRequestDto;
import com.spring.dto.open.item.ItemDto;
import com.spring.model.Item;
import com.spring.model.Status;
import com.spring.model.User;
import com.spring.repository.UserRepository;
import com.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminApiControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminApiControllerV1(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("invalidate")
    public ResponseEntity invalidate(@RequestBody ByIdRequestDto byIdRequestDto) {
        User user = userService.findById(byIdRequestDto.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.NOT_ACTIVE);

        return ResponseEntity.ok(AdminUserDto.fromUser(userService.update(user)));
    }

    @DeleteMapping("delete")
    public ResponseEntity delete(@RequestBody ByIdRequestDto byIdRequestDto) {
        User user = userService.findById(byIdRequestDto.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.DELETED);

        return ResponseEntity.ok(AdminUserDto.fromUser(userService.update(user)));
    }

    @PatchMapping("restore")
    public ResponseEntity restore(@RequestBody ByIdRequestDto byIdRequestDto) {
        User user = userService.findById(byIdRequestDto.getId());
        if (user == null) {
            return ResponseEntity.badRequest().build();
        }
        user.setStatus(Status.ACTIVE);

        return ResponseEntity.ok(AdminUserDto.fromUser(userService.update(user)));
    }
}
