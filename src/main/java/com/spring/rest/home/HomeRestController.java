package com.spring.rest.V1;

import com.spring.dto.V1.open.AuthenticationRequestDtoV1;
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
@RequestMapping(value = "/")
public class HomeRestController {

    @GetMapping
    public ResponseEntity<Map<Object, Object>> home(){
        Map<Object, Object> response = new HashMap<>();

        response.put("message", "Wassup you beautiful bastards");

        return ResponseEntity.ok(response);
    }
}


