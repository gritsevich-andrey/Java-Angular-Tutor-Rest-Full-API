package com.fitness.lessonservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {
    private MediaType json = MediaType.APPLICATION_JSON;

    @Bean
    public RouterFunction<ServerResponse> lessonsEndpoint (LessonsHandler handler){
        return RouterFunctions
                .route(GET("/lessons").and(accept(json)), request -> handler.searchAllLessons())
                .andRoute(GET("/lessons/{id}").and(accept(json)), handler::getLessonById)
                .andRoute(PUT("/lessons/{id}").and(accept(json)), handler::updateLesson)
                .andRoute(DELETE("/lessons/{id}").and(accept(json)), handler::deleteLessonById)
                .andRoute(POST("/lessons/create").and(accept(json)), handler::createLesson);
    }
}
