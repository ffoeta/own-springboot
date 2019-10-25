package com.spring.service.interfaces;

import com.spring.dto.V2.body.UserBodyV2;
import com.spring.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User register(User user, String ROLE);

    User update(User user);

    User findByUsername(String username);

    User findById(UUID id);

    List<User> getAll();

    void deleteById(UUID id);

    User getUser(UserBodyV2 userBodyV2);

    User setIUser(User user, UserBodyV2 userBodyV2);
}
