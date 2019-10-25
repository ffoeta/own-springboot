package com.spring.dto.V2.body;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.spring.model.Item;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ItemBodyV2 {
    private int shift = 0;
    private int to = 0;
    private String id;
    private String name;
    private String categoty;
    private String brand;
}
