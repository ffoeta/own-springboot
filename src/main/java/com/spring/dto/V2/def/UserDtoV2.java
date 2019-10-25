package com.spring.dto.V2.def;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDtoV2 {
    private String username;

    @JsonIgnore
    private String password;

    public User toUser(){

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public static UserDtoV2 fromUser(User user) {
        UserDtoV2 userDtoV2 = new UserDtoV2();

        userDtoV2.setUsername(user.getUsername());
        userDtoV2.setPassword(user.getPassword());

        return userDtoV2;
    }
}