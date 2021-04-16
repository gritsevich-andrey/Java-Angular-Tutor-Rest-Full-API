package com.fitness.instructorservice.utils;

import com.fitness.instructorservice.dto.ProgramDto;
import com.fitness.instructorservice.dto.ProgramRequest;
import com.fitness.instructorservice.models.Program;
import org.springframework.beans.BeanUtils;

public class DocumentDtoUtil {
    public static ProgramDto toDto(Program program) {
        ProgramDto dto = new ProgramDto();
        BeanUtils.copyProperties(program, dto);
        return dto;
    }
    public static Program toDocument(ProgramDto programDto) {
        Program program = new Program();
        BeanUtils.copyProperties(programDto, program);
        return program;
    }
}
