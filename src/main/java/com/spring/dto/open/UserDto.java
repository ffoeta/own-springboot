package com.spring.dto.open;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.User;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {
    private String username;

    public User toUser(){

        User user = new User();

        user.setUsername(username);

        return user;
    }

    public static UserDto fromUser(User user) {
        UserDto userDto = new UserDto();

        userDto.setUsername(user.getUsername());

        return userDto;
    }
}

