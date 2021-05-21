package com.fitness.instructorservice.web;

import com.fitness.instructorservice.web.handlers.BuyingHandler;
import com.fitness.instructorservice.web.handlers.CategoryHandler;
import com.fitness.instructorservice.web.handlers.ProgramsHandler;
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
    public static final String CREATE = "/programs/create";
    public static final String FIND_BY_ID = "/programs/{id}";
    public static final String DELETE_BY_ID = "/programs/{id}";
    public static final String UPDATE = "/programs/update";
    public static final String FIND_ALL = "/programs";

    @Bean
    public RouterFunction<ServerResponse> programEndpoint(ProgramsHandler handler) {
        return RouterFunctions
                .route(GET(FIND_ALL)
                        .and(accept(json)), handler::searchAllPrograms)
                .andRoute(RequestPredicates.POST(CREATE)
                        .and(RequestPredicates.accept(json)), handler::createProgram)
                .andRoute(RequestPredicates.GET(FIND_BY_ID)
                        .and(RequestPredicates.accept(json)), handler::getProgramById)
                .andRoute(RequestPredicates.GET("/programs/find/category")
                        .and(RequestPredicates.accept(json)), handler::getProgramByCategory)
                .andRoute(RequestPredicates.DELETE(DELETE_BY_ID)
                        .and(RequestPredicates.accept(json)), handler::deleteProgramById)
                .andRoute(PATCH(UPDATE).and(accept(json)), handler::updateProgram)
                .andRoute(RequestPredicates.POST("/programs/saveWithCat")
                        .and(RequestPredicates.accept(json)), handler::saveProgramAndCategory)
                .andRoute(RequestPredicates.GET("/programs/template/byCategoryQuery")
                        .and(RequestPredicates.accept(json)), handler::findInstructorQueryDistinct);
    }

    @Bean
    public RouterFunction<ServerResponse> categoryEndpoint(CategoryHandler handler) {
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
    @Bean
    public RouterFunction<ServerResponse> buyingEndpoint(BuyingHandler handler) {
        return RouterFunctions
                .route(GET("/buying")
                        .and(accept(json)), handler::searchAllBuying)
                .andRoute(RequestPredicates.POST("/buying/create")
                        .and(RequestPredicates.accept(json)), handler::createBuying)
                .andRoute(GET("/buying/pupil/{id}").and(accept(json)), handler::getBuyingByCustomerId);
    }
}
