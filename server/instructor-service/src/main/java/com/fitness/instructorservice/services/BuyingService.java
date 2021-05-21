package com.fitness.instructorservice.services;


import com.fitness.instructorservice.models.BuyingPrograms;
import com.fitness.instructorservice.dto.BuyingRequest;
import com.fitness.instructorservice.dto.BuyingResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BuyingService {
    Mono<BuyingResponse> createBuying(BuyingRequest request);
    Flux<BuyingPrograms> findBuyingProgramsByCustomerId(String pupilId);
}
