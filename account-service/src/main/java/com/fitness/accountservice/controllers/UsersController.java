package com.fitness.accountservice.controllers;


import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UsersController {
    // --Commented out by Inspection (10.03.2021 23:32):private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());
    private final UserRepository userRepository;
    private final UserService userService;


    @GetMapping("/lessons/{lesson}")
    public Flux<User> findByLesson(@PathVariable("lesson") String lessonId) {
        return userRepository.findByLessonId(lessonId);
    }

    @GetMapping("/instructors")
    public Flux<User> findInstructors() {
        List<Role> roleList = new ArrayList<>();
        roleList.add(Role.INSTRUCTOR);
        return userRepository.findUserByRoles(roleList);
    }

//    @GetMapping("/users/pages")
//    public Mono<PageSupport<User>> getUserPagination(
//            @RequestParam(name = "page", defaultValue = FIRST_PAGE_NUM) int page,
//            @RequestParam(name = "size", defaultValue = DEFAULT_PAGE_SIZE) int size
//    ) {
//        return userService.getUserPagination(PageRequest.of(page, size));
//    }

}

