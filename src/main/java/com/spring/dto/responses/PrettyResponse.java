package com.spring.dto.responses;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PrettyResponse<T> {
    private int count;
    private List<T> items;


    public PrettyResponse(List<T> items) {
        this.items = items;
        this.count = items.size();
    }

    public PrettyResponse(T t) {
        this.items = new ArrayList<T>();
        this.items.add(t);
        this.count = 1;
    }


}
