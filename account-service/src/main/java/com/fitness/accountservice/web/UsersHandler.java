package com.fitness.accountservice.web;

import com.fitness.accountservice.models.User;
import com.fitness.accountservice.repositories.UserRepository;
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
class UsersHandler {
    private final UserRepository userRepository;
    private MediaType json = MediaType.APPLICATION_JSON;

    public Mono<ServerResponse> searchAllUsers(ServerRequest request) {
        Flux<User> users = userRepository.findAll();
        return ServerResponse.ok()
                .contentType(json)
                .body(users, User.class);
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
        String id = request.pathVariable("id");
        Mono<User> existingUserMono = this.userRepository.findById(id);
        Mono<User> userMono = request.bodyToMono(User.class);
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return userMono.zipWith(existingUserMono,
                (user, existingUser) ->
                        new User(existingUser.getUserId(), user.getEmail(), user.getHash(), user.getSalt(), user.getSecretKey())
        )
                .flatMap(user ->
                        ServerResponse.ok()
                                .contentType(json)
                                .body(userRepository.save(user), User.class)
                ).switchIfEmpty(notFound);
    }

    //Delete user by id
    public Mono<ServerResponse> deleteUserById(ServerRequest request) {
        String id = request.pathVariable("id");
        return ServerResponse.ok()
                .build(userRepository.deleteById(id));
    }
}
