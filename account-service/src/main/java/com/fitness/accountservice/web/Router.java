package com.fitness.accountservice.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class Router {

    private final MediaType json = MediaType.APPLICATION_JSON;

    @Bean
    public RouterFunction<ServerResponse> authEndpoint (AuthHandler handler){
        return RouterFunctions
                .route(POST("/register").and(accept(json)), handler::signup)
                .andRoute(POST("/login").and(accept(json)), handler::login);
    }

    @Bean
    public RouterFunction<ServerResponse> usersEndpoint (UsersHandler handler){
        return RouterFunctions
                .route(GET("/users").and(accept(json)), request -> handler.searchAllUsers())
                .andRoute(GET("/users/{id}").and(accept(json)), handler::getUserById)
                .andRoute(PUT("/users/{id}").and(accept(json)), handler::updateUserRole)
                .andRoute(DELETE("/users/{id}").and(accept(json)), handler::deleteUserById);
    }
}
