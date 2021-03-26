package com.fitness.instructorservice.web.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProgramResponse {
    private String id;
    private String name;
    private String imageSrc;
    private String instructor;
    private double cost;

    public ProgramResponse(String id) {
        this.id = id;
    }
}
