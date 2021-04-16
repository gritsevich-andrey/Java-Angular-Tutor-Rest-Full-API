package com.fitness.accountservice.services;

import com.fitness.accountservice.models.payload.LoginRequest;
import com.fitness.accountservice.models.payload.LoginResponse;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<SignupResponse> signup(SignupRequest request);

    Mono<LoginResponse> login(LoginRequest request);

    Mono<String> parseToken(String token);

}
