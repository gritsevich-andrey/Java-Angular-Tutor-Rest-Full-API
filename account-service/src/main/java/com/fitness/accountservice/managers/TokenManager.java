package com.fitness.accountservice.managers;

import reactor.core.publisher.Mono;

public interface TokenManager {

    String issueToken(String userId);

    Mono<String> parse(String token);
}
