package com.fitness.instructorservice.repository;

import com.fitness.instructorservice.models.ProgramCategory;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableReactiveMongoRepositories
public interface ProgramCategoryRepository extends ReactiveMongoRepository<ProgramCategory, String> {
}
