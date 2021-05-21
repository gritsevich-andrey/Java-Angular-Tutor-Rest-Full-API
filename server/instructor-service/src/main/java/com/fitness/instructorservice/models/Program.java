package com.fitness.instructorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Data
@Document(collection = "programs")
@AllArgsConstructor
@NoArgsConstructor
public class Program extends AbstractDocument<String> implements Serializable {
    @Id
    private String id;
    private String name;
    private String imageSrc;
    @Size(max = 1000)
    private String description;
    private String instructorId;
    private String instructorName;
    private double cost;
    @Field
    private boolean payment = false;
    private List<String> category;
}
