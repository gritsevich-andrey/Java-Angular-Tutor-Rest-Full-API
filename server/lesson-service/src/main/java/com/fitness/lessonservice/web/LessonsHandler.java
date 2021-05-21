package com.fitness.lessonservice.web;

import com.fitness.lessonservice.models.Lesson;
import com.fitness.lessonservice.repository.LessonRepository;
import com.fitness.lessonservice.services.LessonService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LessonsHandler {
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;
    private final MediaType json = MediaType.APPLICATION_JSON;

    public Mono<ServerResponse> searchAllLessons(@NonNull ServerRequest request) {
        Flux<Lesson> lessons = lessonRepository.findAll();
        return ServerResponse.ok()
                .contentType(json)
                .body(lessons, Lesson.class);
    }

    public Mono<ServerResponse> getLessonById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Lesson> lessonMono = lessonRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return lessonMono
                .flatMap(lesson ->
                        ServerResponse.ok()
                                .contentType(json)
                                .bodyValue((lesson))
                                .switchIfEmpty(notFound)
                );
    }

    public Mono<ServerResponse> updateLesson(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Lesson> existingLessonMono = this.lessonRepository.findById(id);
        final Mono<Lesson> lessonMono = request.bodyToMono(Lesson.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return lessonMono
                .zipWith(existingLessonMono,
                        (lesson, existingLesson) ->
                                new Lesson(existingLesson.getId(), lesson.getInstructorId(),
                                        lesson.getPupilId(), lesson.getInstructorName(),
                                        lesson.getCost(), lesson.isPaid(), lesson.isHeld())
                )
                .flatMap(lesson ->
                        ServerResponse.ok()
                                .contentType(json)
                                .body(lessonRepository.save(lesson), Lesson.class)
                ).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteLessonById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .build(lessonRepository.deleteById(id));
    }

    public Mono<ServerResponse> createLesson(@NonNull ServerRequest request) {
        return request.bodyToMono(Lesson.class)
                .flatMap(lesson ->
                        ServerResponse.ok()
                                .contentType(json)
                                .body(lessonRepository.save(lesson), Lesson.class)
                );
    }

    public Mono<ServerResponse> getLessonByPupilId(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        Flux<Lesson> lessonFlux = lessonService.findLessonsByPupilId(id);
        return ServerResponse.ok()
                .contentType(json)
                .body(lessonFlux, Lesson.class);
    }
}
