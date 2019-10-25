package com.spring.dto.V1.admin.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.enums.Status;
import com.spring.model.User;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDtoV1 {
    private UUID id;
    private String username;
    private Status status;

    public User toUser() {
        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setStatus(status);

        return user;
    }

    public static AdminUserDtoV1 fromUser(User user) {
        AdminUserDtoV1 adminUserDtoV1 = new AdminUserDtoV1();

        adminUserDtoV1.setId(user.getId());
        adminUserDtoV1.setUsername(user.getUsername());
        adminUserDtoV1.setStatus(user.getStatus());

        return adminUserDtoV1;
    }
}
