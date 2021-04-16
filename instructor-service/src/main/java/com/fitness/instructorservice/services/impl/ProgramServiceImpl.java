package com.fitness.instructorservice.services.impl;

import com.fitness.instructorservice.dto.ProgramDto;
import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramCategory;
import com.fitness.instructorservice.repository.ProgramCategoryRepository;
import com.fitness.instructorservice.repository.ProgramRepository;
import com.fitness.instructorservice.services.ProgramService;
import com.fitness.instructorservice.dto.ProgramRequest;
import com.fitness.instructorservice.dto.ProgramResponse;
import com.fitness.instructorservice.utils.DocumentDtoUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@AllArgsConstructor
public class ProgramServiceImpl implements ProgramService {
    private final ProgramRepository programRepository;
    private final ProgramCategoryRepository categoryRepository;
    private final ReactiveMongoTemplate reactiveMongoTemplate;

    @Override
    public Mono<ProgramResponse> createProgram(ProgramRequest request) {
        String name = request.getName();
        String imageSrc = request.getImageSrc();
        String instructorId = request.getInstructorId();
        String instructorName = request.getInstructorName();
        String description = request.getDescription();
        boolean payment = request.isPayment();
        double cost = request.getCost();
        List<String> categories = request.getCategory();
        Program program = new Program(null, name, imageSrc, description, instructorId, instructorName, cost, payment, categories);
        return programRepository.save(program)
                .flatMap(programResponse -> {
                    ProgramResponse programResponse1 = new ProgramResponse(programResponse.getId());
                    return Mono.just(programResponse1);
                });
    }

    @Override
    public Flux<ProgramDto> getAllPrograms() {
        String name = "";
         return programRepository.findAll()
                .map(DocumentDtoUtil::toDto);
    }

    @Override
    public Mono<Program> findById(String id) {
        return programRepository.findById(id);
    }


    @Override
    public Mono<Void> deleteById(String id) {
        return programRepository.deleteById(id);
    }

    @Override
    public Flux<ProgramResponse> findByCategory(String category) {
        return programRepository.findByCategory(category);
    }

    @Override
    @Transactional
    public Mono<Void> saveProgramAndCategory(Mono<Program> programMono,
                                             Mono<ProgramCategory> categoryMono) {
        return programMono
                .flatMap(programRepository::save)
                .then(categoryMono)
                .flatMap(categoryRepository::save)
                .then();
    }

    @Override
    public Mono<Program> updateProgram(Program program) {
        return programRepository.save(program);
    }

    @Override
    public Flux<Program> findInstructorQueryDistinct(String category) {
        Query query = new Query();
        query.addCriteria(Criteria .where("instructorName").is(category));
        return reactiveMongoTemplate.find(query, Program.class);
    }
}
