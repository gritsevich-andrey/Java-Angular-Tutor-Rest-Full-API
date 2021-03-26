package com.fitness.instructorservice.web;

import com.fitness.instructorservice.web.handlers.CategoryHandler;
import com.fitness.instructorservice.web.handlers.InstructorHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class Router {
    private final static MediaType json = MediaType.APPLICATION_JSON;
    @Bean
    public RouterFunction<ServerResponse> programEndpoint (InstructorHandler handler){
        return RouterFunctions
                .route(GET("/programs")
                        .and(accept(json)), handler::searchAllPrograms)
                .andRoute(RequestPredicates.POST("/programs/create")
                        .and(RequestPredicates.accept(json)), handler::createProgram)
                .andRoute(RequestPredicates.GET("/programs/{id}")
                        .and(RequestPredicates.accept(json)), handler::getProgramById)
                .andRoute(RequestPredicates.GET("/programs/find/category")
                        .and(RequestPredicates.accept(json)), handler::getProgramByCategory)
                .andRoute(RequestPredicates.DELETE("/programs/{id}")
                        .and(RequestPredicates.accept(json)), handler::deleteProgramById);
    }
    @Bean
    public RouterFunction<ServerResponse> categoryEndpoint (CategoryHandler handler){
        return RouterFunctions
                .route(GET("/categories")
                        .and(accept(json)), handler::findAll)
                .andRoute(RequestPredicates.POST("/categories/create")
                        .and(RequestPredicates.accept(json)), handler::createCategory)
                .andRoute(RequestPredicates.GET("/categories/{id}")
                        .and(RequestPredicates.accept(json)), handler::findById)
                .andRoute(RequestPredicates.DELETE("/categories/{id}")
                        .and(RequestPredicates.accept(json)), handler::delete);
    }
}
