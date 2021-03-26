package com.fitness.instructorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class ProgramCategory {
    @Id
    private String id;
    private String name;
    private String ImageSrc;
}
