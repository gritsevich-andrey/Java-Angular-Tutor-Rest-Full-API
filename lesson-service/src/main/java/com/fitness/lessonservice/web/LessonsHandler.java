package com.fitness.lessonservice.web;

import com.fitness.lessonservice.models.Lesson;
import com.fitness.lessonservice.repository.LessonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class LessonsHandler {
    private final LessonRepository lessonRepository;
    private MediaType json = MediaType.APPLICATION_JSON;

    public Mono<ServerResponse> searchAllLessons() {
        Flux<Lesson> lessons = lessonRepository.findAll();
        return ServerResponse.ok()
                .contentType(json)
                .body(lessons, Lesson.class);
    }

    public Mono<ServerResponse> getLessonById(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<Lesson> lessonMono = lessonRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return lessonMono.flatMap(lesson ->
                ServerResponse.ok()
                        .contentType(json)
                        .body(BodyInserters.fromObject(lesson))
                        .switchIfEmpty(notFound)
        );
    }

    public Mono<ServerResponse> updateLesson(ServerRequest request) {
        String id = request.pathVariable("id");
        Mono<Lesson> existingLessonMono = this.lessonRepository.findById(id);
        Mono<Lesson> lessonMono = request.bodyToMono(Lesson.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return lessonMono.zipWith(existingLessonMono,
                (lesson, existingUser) ->
                        new Lesson(existingUser.getId(), lesson.getName(), lesson.getDate(),
                                lesson.getCost(), lesson.isStatus())
        )
                .flatMap(lesson ->
                        ServerResponse.ok()
                                .contentType(json)
                                .body(lessonRepository.save(lesson), Lesson.class)
                ).switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> deleteLessonById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .build(lessonRepository.deleteById(id));
    }

    public Mono<ServerResponse> createLesson(ServerRequest request) {
        return request.bodyToMono(Lesson.class)
                .flatMap(lesson ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(lessonRepository.save(lesson), Lesson.class)
                );
    }
}
