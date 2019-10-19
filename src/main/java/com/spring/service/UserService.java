package com.spring.service;

import com.spring.model.User;

import java.util.List;
import java.util.UUID;

public interface UserService {
    User register(User user, String ROLE);

    User findByUsername(String username);

    User findById(UUID id);

    List<User> getAll();

    void delete(UUID id);
}
