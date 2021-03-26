package com.fitness.instructorservice.web.payload;

import com.fitness.instructorservice.models.ProgramCategory;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class ProgramRequest {
    private String name;
    private String imageSrc;
    private String instructor;
    private double cost;
    private List<String> category = new ArrayList<>();
}
