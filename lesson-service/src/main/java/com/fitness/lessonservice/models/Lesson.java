package com.fitness.lessonservice.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document (collection = "lessons")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lesson {
    @Id
    private String id;
    private String name;
    private String date;
    private double cost;
    private boolean status;
    @Transient
    private List<User> users;
    public Lesson(List<User> users) {
        this.users = users;
    }

    public Lesson(String id, String name, String date, double cost, boolean status) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.status = status;

    }
}
