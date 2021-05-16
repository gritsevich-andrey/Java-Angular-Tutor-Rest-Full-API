package com.fitness.instructorservice.web.handlers;

import com.fitness.instructorservice.dto.ProgramDto;
import com.fitness.instructorservice.dto.ProgramRequest;
import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.models.ProgramCategory;
import com.fitness.instructorservice.repository.ProgramRepository;
import com.fitness.instructorservice.services.ProgramService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class ProgramsHandler {
    private final ProgramRepository programRepository;
    private final ProgramService programService;
    private final static MediaType json = MediaType.APPLICATION_JSON;

    public Mono<ServerResponse> searchAllPrograms(ServerRequest request) {
        Flux<Program> programsFlux = programRepository.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(json)
                .body(programsFlux, Program.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getAllPrograms(ServerRequest request) {
        return ok()
                .contentType(json)
                .body(programService.getAllPrograms(), ProgramDto.class).log();
    }

    public Mono<ServerResponse> createProgram(@NonNull ServerRequest request) {
        final Mono<ProgramRequest> program = request.bodyToMono(ProgramRequest.class);
        return program.flatMap(programService::createProgram)
                .flatMap(data -> ServerResponse.ok().contentType(json)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> getProgramById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        return programService.findById(id)
                .flatMap(data ->
                        ServerResponse.ok()
                                .contentType(json)
                                .bodyValue(data)
                                .switchIfEmpty(ServerResponse.notFound().build()));
    }

    public Mono<ServerResponse> getProgramByCategory(@NonNull ServerRequest request) {
        String category = request.queryParam("category").get();
        return ServerResponse.ok()
                .contentType(json)
                .body(programService.findByCategory(category), Program.class).log();
    }

    public Mono<ServerResponse> deleteProgramById(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        return ok()
                .contentType(json)
                .body(programService.deleteById(id), Void.class).log();
    }

    public Mono<ServerResponse> saveProgramAndCategory(@NonNull ServerRequest request) {
        Program program = new Program();
        program.setName("Профессиональный бодифлекс для дома");
        ProgramCategory category = new ProgramCategory();
        category.setName("Бодифлекс");
        return ServerResponse.ok()
                .contentType(json)
                .body(programService.saveProgramAndCategory(Mono.just(program), Mono.just(category)), Void.class)
                .log();
    }

    public Mono<ServerResponse> updateProgram(@NonNull ServerRequest request) {
        final Mono<Program> program = request.bodyToMono(Program.class);
        return program.flatMap(programService::updateProgram)
                .flatMap(data -> ServerResponse.ok().contentType(json)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> findInstructorQueryDistinct(ServerRequest request) {
        String category = request.queryParam("category").get();
        return ok()
                .contentType(json)
                .body(programService.findInstructorQueryDistinct(category), Program.class).log();
    }
}
