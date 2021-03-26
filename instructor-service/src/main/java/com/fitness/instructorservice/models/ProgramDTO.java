package com.fitness.instructorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class ProgramDTO {
    private String id;
    private String name;
    private String imageSrc;
    private String instructor;
    private List<ProgramCategory> category = new ArrayList<>();
}
