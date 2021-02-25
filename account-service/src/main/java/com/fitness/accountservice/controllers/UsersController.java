package com.fitness.accountservice.controllers;//package tech.home.fitness.controllers;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.RestController;
//import tech.home.fitness.repositories.UserRepository;
//
//@RestController
//@CrossOrigin(origins = {"http://localhost:4200"})
//public class UsersController {
//    @Autowired
//    private UserRepository usersRepository;
//
//
////    @GetMapping("/users")
////    public Flux<User> getAllUsers() {
////        return usersRepository.findAll();
////    }
//
////    @GetMapping("/users/{id}")
////    public Mono<ResponseEntity<User>> getUserById(@PathVariable(value = "id") String userId) {
////        return usersRepository.findById(userId)
////                .map(savedUsers -> ResponseEntity.ok(savedUsers))
////                .defaultIfEmpty(ResponseEntity.notFound().build());
////    }
//
////    @PutMapping("/users/{id}")
////    public Mono<ResponseEntity<User>> updateUser(@PathVariable(value = "id") String userId,
////                                                 @Valid @RequestBody User user) {
////        return usersRepository.findById(userId)
////                .flatMap(existingUsers -> {
////                    existingUsers.setEmail(user.getEmail());
////                    return usersRepository.save(existingUsers);
////                })
////                .map(updatedUsers -> new ResponseEntity<>(updatedUsers, HttpStatus.OK))
////                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
////    }
//
////    @DeleteMapping("/users/{id}")
////    public Mono<Void> DeleteUser(@PathVariable(value = "id") String userId) {
////        return usersRepository.deleteById(userId);
////    }
//}
//
