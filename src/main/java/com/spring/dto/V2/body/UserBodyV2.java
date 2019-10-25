package com.spring.dto.V2.body;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.UUID;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserBodyV2 {
    private int shift;
    private int to;
    private UUID id;
    private String username;
    private String password;
}
