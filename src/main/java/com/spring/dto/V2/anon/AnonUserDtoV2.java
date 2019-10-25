package com.spring.dto.V2.anon;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.User;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AnonUserDtoV2 {
    private String username;

    @JsonIgnore
    private String password;

    public User toUser(){

        User user = new User();

        user.setUsername(username);
        user.setPassword(password);

        return user;
    }

    public static AnonUserDtoV2 fromUser(User user) {
        AnonUserDtoV2 anonUserDtoV2 = new AnonUserDtoV2();

        anonUserDtoV2.setUsername(user.getUsername());
        anonUserDtoV2.setPassword(user.getPassword());

        return anonUserDtoV2;
    }
}