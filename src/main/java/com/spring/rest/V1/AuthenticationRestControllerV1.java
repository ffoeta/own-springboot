package com.spring.rest.V1;

import com.spring.dto.AuthenticationRequestDto;
import com.spring.model.User;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import javax.security.sasl.AuthenticationException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "/api/v1/auth/")
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    @Autowired
    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
        System.out.println("LOGIN");
        String username = requestDto.getUsername();
        System.out.println("1");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        System.out.println("2");
        User user = userService.findByUsername(username);
        System.out.println("3");

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }
        System.out.println("4");

        String token = jwtTokenProvider.createToken(username, user.getRoles());

        System.out.println("5");

        Map<Object, Object> response = new HashMap<>();

        System.out.println("6");
        response.put("username", username);
        response.put("token", token);

        System.out.println("7");

        return ResponseEntity.ok(response);
    }

    @PostMapping("sign")
    public ResponseEntity sign(@RequestBody AuthenticationRequestDto requestDto) {

        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());

        if (user.getUsername().startsWith("admin")) {
            userService.register(user,"ADMIN_USER");
        } else {
            userService.register(user,"ROLE_USER");
        }

        return login(requestDto);
    }

    @GetMapping("me")
    public ResponseEntity me(@RequestHeader("Authorization") String header){
        Map<Object, Object> response = new HashMap<>();
        header = header.substring(7);
        String username = jwtTokenProvider.getUsername(header);
        response.put("username", username);
        response.put("token", header);

        return ResponseEntity.ok(response);
    }
}

