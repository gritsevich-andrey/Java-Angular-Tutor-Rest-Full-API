package com.fitness.instructorservice.services;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramCategory;
import com.fitness.instructorservice.dto.ProgramRequest;
import com.fitness.instructorservice.dto.ProgramResponse;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public interface ProgramService {
    Mono<ProgramResponse> createProgram(ProgramRequest request);
    Mono<Program> findById(String id);
    Mono<Void> deleteById(String id);
    Flux<ProgramResponse> findByCategory(String category);
    Mono<Void> saveProgramAndCategory(Mono<Program> programMono, Mono<ProgramCategory> categoryMono);
    Mono<Program> updateProgram(Program program);
    Flux<Program> findInstructorQueryDistinct(String category);
}
