package com.fitness.instructorservice.dto;

import lombok.Data;

import java.util.List;

@Data
public class BuyingRequest {

    private String customerId;
    private String name;
    private String imageSrc;
    private String description;
    private String instructorId;
    private String instructorName;
    private List<String> category;
    private double cost;
    private boolean payment;
}
