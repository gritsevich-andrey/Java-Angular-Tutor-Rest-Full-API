package com.fitness.instructorservice.services.impl;

import com.fitness.instructorservice.models.BuyingPrograms;
import com.fitness.instructorservice.repository.BuyingRepository;
import com.fitness.instructorservice.services.BuyingService;
import com.fitness.instructorservice.dto.BuyingRequest;
import com.fitness.instructorservice.dto.BuyingResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;
@Service
@AllArgsConstructor
public class BuyingServiceImpl implements BuyingService {
    private final BuyingRepository buyingRepository;

    @Override
    public Mono<BuyingResponse> createBuying(BuyingRequest request) {
        BuyingPrograms buying = BuyingPrograms.builder()
                .customerId(request.getCustomerId())
                .name(request.getName())
                .imageSrc(request.getImageSrc())
                .description(request.getDescription())
                .instructorId(request.getInstructorId())
                .instructorName(request.getInstructorName())
                .category(request.getCategory())
                .cost(request.getCost())
                .payment(request.isPayment())
                .date(new Date())
                .build();

        return buyingRepository.save(buying)
                .flatMap(data -> {
                    BuyingResponse buyingResponse = new BuyingResponse(data.getId());
                    return Mono.just(buyingResponse);
                });
    }

    @Override
    public Flux<BuyingPrograms> findBuyingProgramsByCustomerId(String pupilId) {
        return buyingRepository.findBuyingProgramsByCustomerId(pupilId);
    }
}
