package com.fitness.instructorservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ProgramResponse {
    private String id;
    private String name;
    private String imageSrc;
    private String description;
    private String instructor;
    private boolean payment;
    private double cost;
    private List<String> category;
    private List<String> pupilId;

    public ProgramResponse(String id) {
        this.id = id;
    }
}
