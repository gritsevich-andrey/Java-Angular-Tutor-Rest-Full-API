package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.BuyingPrograms;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@EnableReactiveMongoRepositories
public interface BuyingRepository extends ReactiveMongoRepository<BuyingPrograms, String> {
    @NonNull
    Flux<BuyingPrograms> findAll();

    @NonNull
    Flux<BuyingPrograms> findBuyingProgramsByCustomerId(String pupilId);
}
