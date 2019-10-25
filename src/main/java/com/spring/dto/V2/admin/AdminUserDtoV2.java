package com.spring.dto.V2.admin;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.User;
import com.spring.model.enums.Status;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AdminUserDtoV2 {
    private UUID id;
    private String username;
    private Status status;


    public User toUser(){

        User user = new User();

        user.setId(id);
        user.setUsername(username);
        user.setStatus(status);

        return user;
    }



    public static AdminUserDtoV2 fromUser(User user) {
        AdminUserDtoV2 adminUserDtoV2 = new AdminUserDtoV2();

        adminUserDtoV2.setId(user.getId());
        adminUserDtoV2.setUsername(user.getUsername());
        adminUserDtoV2.setStatus(user.getStatus());

        return adminUserDtoV2;
    }
}
