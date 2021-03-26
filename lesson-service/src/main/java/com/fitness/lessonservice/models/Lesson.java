package com.fitness.lessonservice.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Document (collection = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
//    @Transient
//    public static final String SEQUENCE_NAME = "lessons_sequence";
    @Id
    private String id;
    private String name;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate date = LocalDate.now();
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    private LocalTime time = LocalTime.now();
    private double cost;
    private boolean status;
    @Transient
    private List<User> users;
    public Lesson(List<User> users) {
        this.users = users;
    }

    public Lesson(String id, String name, double cost, boolean status) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.status = status;

    }

}
