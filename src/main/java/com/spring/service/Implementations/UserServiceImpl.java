package com.spring.service.Implementations;

import com.spring.model.Role;
import com.spring.model.enums.Status;
import com.spring.model.User;
import com.spring.repository.RoleRepository;
import com.spring.repository.UserRepository;
import com.spring.service.interfaces.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user, String ROLE) {
        Role role = roleRepository.findByName(ROLE);
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.ACTIVE);
        user.setId(UUID.randomUUID());
        Date date = new Date();
        user.setCreated(date);
        user.setUpdated(date);
        userRepository.save(user);
        return user;
    }

    @Override
    public User update(User user) {
        user.setUpdated(new Date());
        return userRepository.save(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return user;
    }

    @Override
    public User findById(UUID id) {
        User user = userRepository.findById(id).orElse(null);
        return user;
    }

    @Override
    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
