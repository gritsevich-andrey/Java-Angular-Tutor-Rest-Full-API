package com.fitness.instructorservice.services;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramCategory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProgramCategoryService {
    Mono<ProgramCategory> findById(String id);

    Flux<ProgramCategory> findAll();

    Mono<Void> deleteById(String id);

    Mono<ProgramCategory> createCategory(ProgramCategory programCategory);
}