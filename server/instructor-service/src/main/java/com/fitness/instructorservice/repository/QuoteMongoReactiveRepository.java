package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.Program;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.data.repository.reactive.ReactiveSortingRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
@Repository
@EnableReactiveMongoRepositories
public interface QuoteMongoReactiveRepository extends ReactiveSortingRepository<Program, String> {
    Flux<Program> findAllByIdOrderByIdAsc (Pageable page);
}
