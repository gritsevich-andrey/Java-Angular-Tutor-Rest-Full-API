package com.fitness.instructorservice.services;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramDTO;
import reactor.core.publisher.Mono;

public interface ConvertService {
     ProgramDTO covertProgramToProgramDTO(Program program);
    Mono<Program> covertProgramDTOToProgram(ProgramDTO programDTO);
}
