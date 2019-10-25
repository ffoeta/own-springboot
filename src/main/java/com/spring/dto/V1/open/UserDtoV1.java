package com.spring.dto.V1.open;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDtoV1 {
    private String username;

    public User toUser(){

        User user = new User();

        user.setUsername(username);

        return user;
    }

    public static UserDtoV1 fromUser(User user) {
        UserDtoV1 userDtoV1 = new UserDtoV1();

        userDtoV1.setUsername(user.getUsername());

        return userDtoV1;
    }
}

