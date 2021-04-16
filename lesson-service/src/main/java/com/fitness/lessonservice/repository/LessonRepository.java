package com.fitness.lessonservice.repository;

import com.fitness.lessonservice.models.Lesson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Flux;

@EnableReactiveMongoRepositories
public interface LessonRepository extends ReactiveMongoRepository<Lesson, String> {
    Flux<Lesson> findLessonsByPupilId(String pupilId);
}
