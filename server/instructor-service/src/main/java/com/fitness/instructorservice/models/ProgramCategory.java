package com.fitness.instructorservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Document(collection = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class ProgramCategory extends AbstractDocument<String> implements Serializable {
    @Id
    private String id;
    @NotBlank
    private String name;
    private String ImageSrc;
}
