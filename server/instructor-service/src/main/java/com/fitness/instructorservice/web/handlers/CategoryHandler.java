package com.fitness.instructorservice.web.handlers;

import com.fitness.instructorservice.models.ProgramCategory;
import com.fitness.instructorservice.services.ProgramCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Component
@RequiredArgsConstructor
public class CategoryHandler {
    private final ProgramCategoryService programCategoryService;

    public Mono<ServerResponse> createCategory(ServerRequest request) {
        return request.bodyToMono(ProgramCategory.class)
                .flatMap(programCategoryService::createCategory)
                .flatMap(data -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data));
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(programCategoryService.findAll(), ProgramCategory.class);
    }

    public Mono<ServerResponse> findById(ServerRequest request) {

        String id = request.pathVariable("id");

        return programCategoryService.findById(id)
                .flatMap(data -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(data))
                .switchIfEmpty(ServerResponse.notFound().build());

    }

    public Mono<ServerResponse> delete(ServerRequest request) {

        String id = request.pathVariable("id");

        return ok()

                .contentType(MediaType.APPLICATION_JSON)

                .body(programCategoryService.deleteById(id), Void.class).log();

    }
}