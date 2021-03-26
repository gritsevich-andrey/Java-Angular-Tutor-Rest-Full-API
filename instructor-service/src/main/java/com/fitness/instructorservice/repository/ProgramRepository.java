package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.web.payload.ProgramResponse;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@EnableReactiveMongoRepositories
public interface ProgramRepository extends ReactiveMongoRepository<Program, String> {
    Flux<ProgramResponse> findByCategory(String category);
}
