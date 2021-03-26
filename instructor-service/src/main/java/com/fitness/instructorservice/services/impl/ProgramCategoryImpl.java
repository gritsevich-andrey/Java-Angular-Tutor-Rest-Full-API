package com.fitness.instructorservice.services.impl;

import com.fitness.instructorservice.models.ProgramCategory;
import com.fitness.instructorservice.repository.ProgramCategoryRepository;
import com.fitness.instructorservice.services.ProgramCategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProgramCategoryImpl implements ProgramCategoryService {
    private final ProgramCategoryRepository programCategoryRepository;
    @Override
    public Mono<ProgramCategory> findById(String id) {
        return programCategoryRepository.findById(id);
    }

    @Override
    public Flux<ProgramCategory> findAll() {
        return programCategoryRepository.findAll();
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return programCategoryRepository.deleteById(id);
    }

    @Override
    public Mono<ProgramCategory> createCategory(ProgramCategory programCategory) {
        return programCategoryRepository.save(programCategory);
    }
}
