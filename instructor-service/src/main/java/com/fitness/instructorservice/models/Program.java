package com.fitness.instructorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collection = "programs")
@AllArgsConstructor
@NoArgsConstructor
public class Program {
    @Id
    private String id;
    private String name;
    private String imageSrc;
    private String instructor;
    private double cost;
    private List<String> category;
}
