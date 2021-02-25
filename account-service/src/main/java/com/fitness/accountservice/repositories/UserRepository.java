package com.fitness.accountservice.repositories;

import com.fitness.accountservice.models.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import reactor.core.publisher.Mono;


@EnableReactiveMongoRepositories
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    Mono<User> findByEmail (String email);

}
