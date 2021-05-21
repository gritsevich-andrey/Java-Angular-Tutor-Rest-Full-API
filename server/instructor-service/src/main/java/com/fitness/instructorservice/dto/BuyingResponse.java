package com.fitness.instructorservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyingResponse {
    private String id;

    public BuyingResponse(String id) {
        this.id = id;
    }
}
