package com.fitness.accountservice.services;

import com.fitness.accountservice.dto.SignupRequest;
import com.fitness.accountservice.dto.SignupResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
    Mono<SignupResponse> updateUserRole(SignupRequest request);
    Mono<SignupResponse> updateUserProfile(SignupRequest request);
}