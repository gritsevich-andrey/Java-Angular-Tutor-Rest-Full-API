package com.fitness.instructorservice.services;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.web.payload.ProgramRequest;
import com.fitness.instructorservice.web.payload.ProgramResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public interface ProgramService {
    Mono<ProgramResponse> createProgram(ProgramRequest request);

    Mono<Program> findById(String id);

    Flux<Program> findAll();

    Mono<Void> deleteById(String id);

    Flux<ProgramResponse> findByCategory(String category);
}
