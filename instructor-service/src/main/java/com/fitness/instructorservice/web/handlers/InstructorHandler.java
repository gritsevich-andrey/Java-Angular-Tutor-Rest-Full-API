package com.fitness.instructorservice.web.handlers;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.repository.ProgramRepository;
import com.fitness.instructorservice.services.ProgramService;
import com.fitness.instructorservice.web.payload.ProgramRequest;
import com.fitness.instructorservice.web.payload.ProgramResponse;
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
public class InstructorHandler {
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

    public Mono<ServerResponse> createProgram(ServerRequest request) {
        final Mono<ProgramRequest> program = request.bodyToMono(ProgramRequest.class);
        return program.flatMap(programService::createProgram)
                .flatMap(data -> ServerResponse.ok().contentType(json)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> getProgramById(ServerRequest request) {
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
}
