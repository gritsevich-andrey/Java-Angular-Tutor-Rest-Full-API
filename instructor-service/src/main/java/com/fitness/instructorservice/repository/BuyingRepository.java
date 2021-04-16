package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.BuyingPrograms;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
@EnableReactiveMongoRepositories
public interface BuyingRepository extends ReactiveMongoRepository<BuyingPrograms, String> {
    Flux<BuyingPrograms> findAll();

    Flux<BuyingPrograms> findBuyingProgramsByCustomerId(String pupilId);
}
