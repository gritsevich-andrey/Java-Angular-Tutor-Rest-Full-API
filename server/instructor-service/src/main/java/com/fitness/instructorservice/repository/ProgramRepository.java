package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.dto.ProgramResponse;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@EnableReactiveMongoRepositories
public interface ProgramRepository extends ReactiveMongoRepository<Program, String> {
    @Query("{'category': ?0}")
    Flux<ProgramResponse> findByCategory(String category);
    @NonNull
    Flux<Program> findAll();
}
