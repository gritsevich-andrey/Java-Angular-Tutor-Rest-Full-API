package com.fitness.lessonservice.controllers;
import com.fitness.lessonservice.models.Lesson;
import com.fitness.lessonservice.models.User;
import com.fitness.lessonservice.repository.LessonRepository;
import com.fitness.lessonservice.services.LessonMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class LessonController {
private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private WebClient.Builder webClientBuilder;
    @Autowired
    private LessonRepository repository;

    @GetMapping("/lessons/{id}/with-accounts")
    public Mono<Lesson> findByIdWithAccounts(@PathVariable("id") String id) {
        Flux<User> accounts = webClientBuilder.build().get().uri("http://account-service/lessons/{lesson}", id)
                .retrieve().bodyToFlux(User.class);
        return accounts
                .collectList()
                .map(Lesson::new)
                .mergeWith(repository.findById(id))
                .collectList()
                .map(LessonMapper::map);
    }
}
