package com.fitness.instructorservice.dto;

import lombok.Data;

@Data
public class BuyingResponse {
    private String id;

    public BuyingResponse(String id) {
        this.id = id;
    }
}
