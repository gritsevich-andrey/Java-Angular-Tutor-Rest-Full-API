package com.fitness.instructorservice.services.impl;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramDTO;
import com.fitness.instructorservice.services.ConvertService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
@Service
public class ConvertServiceImpl implements ConvertService {
    @Override
    public ProgramDTO covertProgramToProgramDTO(Program program) {
//        return new ProgramDTO(program.getId(), program.getName(), program.getImageSrc(),
//                program.getInstructor(), program.getCategory());
    return null;
    }

    @Override
    public Mono<Program> covertProgramDTOToProgram(ProgramDTO programDTO) {
//        return Mono.just(new Program(programDTO.getId(), programDTO.getName(),
//                programDTO.getImageSrc(), programDTO.getInstructor(), programDTO.getCategory()));
   return null;
    }
}
