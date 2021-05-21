package com.fitness.accountservice.web;

import com.fitness.accountservice.errors.LoginDeniedException;
import com.fitness.accountservice.dto.LoginRequest;
import com.fitness.accountservice.dto.LoginResponse;
import com.fitness.accountservice.dto.SignupRequest;
import com.fitness.accountservice.dto.SignupResponse;
import com.fitness.accountservice.services.AuthService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthHandler {

    private final MediaType json = MediaType.APPLICATION_JSON;
    private final AuthService service;

    public Mono<ServerResponse> signup(@NonNull ServerRequest request) {
        Mono<SignupRequest> values = request.bodyToMono(SignupRequest.class);
        Mono<SignupResponse> result = values.flatMap(service::signup);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .onErrorResume(error -> ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> login(@NonNull ServerRequest request) {
        Mono<LoginRequest> body = request.bodyToMono(LoginRequest.class);
        Mono<LoginResponse> result = body.flatMap(service::login);
        return result.flatMap(data -> ServerResponse.ok().contentType(json).bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(error -> {
                    if (error instanceof LoginDeniedException) {
                        return ServerResponse.badRequest().build();
                    }
                    return ServerResponse.status(500).build();
                });
    }
}
