package com.fitness.instructorservice.dto;

import lombok.Data;

import java.util.List;
@Data
public class ProgramDto {
    private String id;
    private String name;
    private String imageSrc;
    private String description;
    private String instructorId;
    private String instructorName;
    private double cost;
    private boolean payment = false;
    private List<String> category;
}
