package com.fitness.instructorservice.services;

import com.fitness.instructorservice.dto.ProgramDto;
import com.fitness.instructorservice.models.Program;
import reactor.core.publisher.Mono;

public interface ConvertService {
    ProgramDto covertProgramToProgramDTO(Program program);

    Mono<Program> covertProgramDTOToProgram(ProgramDto programDTO);
}
