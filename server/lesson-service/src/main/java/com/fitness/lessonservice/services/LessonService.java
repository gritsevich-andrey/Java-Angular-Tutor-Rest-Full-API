package com.fitness.lessonservice.services;

import com.fitness.lessonservice.models.Lesson;
import reactor.core.publisher.Flux;

public interface LessonService {
    Flux<Lesson> findLessonsByPupilId(String pupilId);
}
