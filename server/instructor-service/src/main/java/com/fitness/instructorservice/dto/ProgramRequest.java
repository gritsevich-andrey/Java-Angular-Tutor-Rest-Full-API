package com.fitness.instructorservice.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

@Data
public class ProgramRequest {
    private String name;
    private String imageSrc;
    private String description;
    private String instructorId;
    @NotBlank
    private String instructorName;
    private boolean payment;
    private double cost;
    private List<String> category = new ArrayList<>();
}
