package com.fitness.instructorservice.web.handlers;

import com.fitness.instructorservice.models.BuyingPrograms;
import com.fitness.instructorservice.repository.BuyingRepository;
import com.fitness.instructorservice.services.BuyingService;
import com.fitness.instructorservice.dto.BuyingRequest;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BuyingHandler {
    private final BuyingRepository buyingRepository;
    private final BuyingService buyingService;
    private final static MediaType json = MediaType.APPLICATION_JSON;

    public Mono<ServerResponse> searchAllBuying(ServerRequest request) {
        Flux<BuyingPrograms> buyingFlux = buyingRepository.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(json)
                .body(buyingFlux, BuyingPrograms.class)
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> createBuying(@NonNull ServerRequest request) {
        final Mono<BuyingRequest> program = request.bodyToMono(BuyingRequest.class);
        return program.flatMap(buyingService::createBuying)
                .flatMap(data -> ServerResponse.ok().contentType(json)
                        .bodyValue(data));
    }
    public Mono<ServerResponse> searchAllPrograms(ServerRequest request) {
        Flux<BuyingPrograms> buyingFlux = buyingRepository.findAll();
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        return ServerResponse.ok()
                .contentType(json)
                .body(buyingFlux, BuyingPrograms.class)
                .switchIfEmpty(notFound);
    }
    public Mono<ServerResponse> getBuyingByCustomerId(@NonNull ServerRequest request) {
        String id = request.pathVariable("id");
        Flux<BuyingPrograms> lessonFlux = buyingService.findBuyingProgramsByCustomerId(id);
        return ServerResponse.ok()
                .contentType(json)
                .body(lessonFlux, BuyingPrograms.class);
    }
}
