package com.fitness.accountservice.controllers;//package tech.home.fitness.controllers;


import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.repositories.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"})
public class UsersController {
    // --Commented out by Inspection (10.03.2021 23:32):private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());
    @Autowired
    private UserRepository userRepository;


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

