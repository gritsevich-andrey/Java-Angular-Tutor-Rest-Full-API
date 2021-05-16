package com.fitness.accountservice.services;

import com.fitness.accountservice.dto.LoginRequest;
import com.fitness.accountservice.dto.LoginResponse;
import com.fitness.accountservice.dto.SignupRequest;
import com.fitness.accountservice.dto.SignupResponse;
import reactor.core.publisher.Mono;

public interface AuthService {

    Mono<SignupResponse> signup(SignupRequest request);

    Mono<LoginResponse> login(LoginRequest request);

    Mono<String> parseToken(String token);

}
