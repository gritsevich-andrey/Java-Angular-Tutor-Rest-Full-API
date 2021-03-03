package com.fitness.accountservice.web;

import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;


@Component
@RequiredArgsConstructor
class UsersHandler {
    private final UserRepository userRepository;
    private MediaType json = MediaType.APPLICATION_JSON;
    private final UserService service;
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());

    public Mono<ServerResponse> searchAllUsers() {
        Flux<User> users = userRepository.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(json)
                .body(users, User.class)
                .switchIfEmpty(notFound);
    }

    //Get User by ID
    public Mono<ServerResponse> getUserById(ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<User> userMono = userRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return userMono.flatMap(user ->
                ServerResponse.ok()
                        .contentType(json)
                        .body(BodyInserters.fromObject(user))
                        .switchIfEmpty(notFound)
        );
    }

    //Update user by ID
    public Mono<ServerResponse> updateUser(ServerRequest request) {
        Mono<SignupRequest> body = request.bodyToMono(SignupRequest.class);
        Mono<SignupResponse> result = body.flatMap(service::updateUser);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    //Delete user by id
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .build(userRepository.deleteById(id));
    }
}
