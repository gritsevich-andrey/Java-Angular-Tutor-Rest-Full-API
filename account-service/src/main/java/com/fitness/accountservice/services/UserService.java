package com.fitness.accountservice.services;

import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.payload.PageSupport;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface UserService {
    Mono<SignupResponse> updateUserRole(SignupRequest request);
    Mono<SignupResponse> updateUserProfile(SignupRequest request);

    Mono<PageSupport<User>> getUserPagination(SpringDataWebProperties.Pageable page);
}