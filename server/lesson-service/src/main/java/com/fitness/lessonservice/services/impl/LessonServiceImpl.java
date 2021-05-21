package com.fitness.lessonservice.services.impl;

import com.fitness.lessonservice.models.Lesson;
import com.fitness.lessonservice.repository.LessonRepository;
import com.fitness.lessonservice.services.LessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    private final LessonRepository lessonRepository;
    @Override
    public Flux<Lesson> findLessonsByPupilId(String pupilId) {
        return lessonRepository.findLessonsByPupilId(pupilId);
    }
}
