package com.fitness.accountservice.web;

import com.fitness.accountservice.dto.SignupRequest;
import com.fitness.accountservice.dto.SignupResponse;
import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.UserService;
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
class UsersHandler {
    private final UserRepository userRepository;
    private final static MediaType JSON = MediaType.APPLICATION_JSON;
    private final UserService service;

    public Mono<ServerResponse> searchAllUsers() {
        Flux<User> users = userRepository.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(JSON)
                .body(users, User.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> searchAllInstructors() {
        Flux<User> users = userRepository.findAll().filter(user -> user.getRoles().contains(Role.INSTRUCTOR));
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(JSON)
                .body(users, User.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getUserById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");

        Mono<User> userMono = userRepository.findById(id);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();

        return userMono.flatMap(user ->
                ServerResponse.ok()
                        .contentType(JSON)
                        .bodyValue(user)
                        .switchIfEmpty(notFound)
        );
    }

    public Mono<ServerResponse> updateUserRole(@NonNull ServerRequest request) {
        Mono<SignupRequest> monoRole = request.bodyToMono(SignupRequest.class);
        Mono<SignupResponse> result = monoRole.flatMap(service::updateUserRole);
        return result.flatMap(data -> ServerResponse.ok().contentType(JSON).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> updateUserProfile(@NonNull ServerRequest request) {
        Mono<SignupRequest> monoProfile = request.bodyToMono(SignupRequest.class);
        Mono<SignupResponse> result = monoProfile.flatMap(service::updateUserProfile);
        return result.flatMap(data -> ServerResponse.ok().contentType(JSON).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> deleteUserById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .build(userRepository.deleteById(id)).log();
    }
}
