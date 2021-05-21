package com.fitness.accountservice.controllers;


import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.repositories.UserRepository;
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
    private final UserRepository userRepository;

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

}

