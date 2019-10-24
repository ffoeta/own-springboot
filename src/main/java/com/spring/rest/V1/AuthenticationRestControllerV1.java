package com.spring.rest.V1;

import com.spring.dto.open.AuthenticationRequestDto;
import com.spring.model.User;
import com.spring.security.jwt.JwtTokenProvider;
import com.spring.service.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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
        String username = requestDto.getUsername();
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
        User user = userService.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        String token = jwtTokenProvider.createToken(username, user.getRoles());


        Map<Object, Object> response = new HashMap<>();

        response.put("username", username);
        response.put("token", token);


        return ResponseEntity.ok(response);
    }

    @PostMapping("sign")
    public ResponseEntity sign(@RequestBody AuthenticationRequestDto requestDto) {

        User user = new User();
        user.setUsername(requestDto.getUsername());
        user.setPassword(requestDto.getPassword());

        userService.register(user,"ROLE_USER");

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

