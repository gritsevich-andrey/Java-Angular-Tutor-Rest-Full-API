package com.fitness.lessonservice.services;

import com.fitness.lessonservice.models.Lesson;

import java.util.List;

public class LessonMapper {
    public static Lesson map(List<Lesson> lessons) {
        Lesson lesson = new Lesson();
        for (Lesson les : lessons) {
            if (les.getUsers() != null) lesson.setUsers(les.getUsers());
            if (les.getName() != null) lesson.setName(les.getName());
            if (les.getDate() != null) lesson.setDate(les.getDate());
            if (les.getCost() != 0) lesson.setCost(les.getCost());
        }
        return lesson;
    }
}
