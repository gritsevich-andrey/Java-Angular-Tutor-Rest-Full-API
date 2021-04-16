package com.fitness.instructorservice.controllers;

import com.fitness.instructorservice.models.Program;
import com.fitness.instructorservice.repository.QuoteMongoReactiveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;

@RequiredArgsConstructor
@RestController
public class ProgramReactiveController {
    private static final int DELAY_PER_ITEM_MS = 100;
    private QuoteMongoReactiveRepository mongoReactiveRepository;

    public ProgramReactiveController(QuoteMongoReactiveRepository mongoReactiveRepository) {
        this.mongoReactiveRepository = mongoReactiveRepository;
    }
    @GetMapping("/programs/quotes")
    public Flux<Program> getQuoteFlux(
            @RequestParam(name = "page") int page,
                                      @RequestParam(name = "size") int size
    ) {
        int i = 5;
        return mongoReactiveRepository.findAllByIdOrderByIdAsc(PageRequest.of(1, 5))
                .delayElements(Duration.ofMillis(DELAY_PER_ITEM_MS));
    }
}
