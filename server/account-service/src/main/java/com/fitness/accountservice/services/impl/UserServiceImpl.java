package com.fitness.accountservice.services.impl;

import com.fitness.accountservice.dto.SignupRequest;
import com.fitness.accountservice.dto.SignupResponse;
import com.fitness.accountservice.errors.AlreadyExistsException;
import com.fitness.accountservice.managers.TokenManager;
import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.UserProfile;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Component("UserService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final TokenManager tokenManager;
    private final UserRepository repository;
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Mono<SignupResponse> updateUserRole(SignupRequest request) {

        String userId = request.getUserId();
        List<Role> roles = request.getRoles();
        return repository.findByUserId(userId)
                .flatMap(result -> {
                    if (result.getUserId() != null) {
                        return repository.save(new User(userId, result.getEmail(), result.getHash(), result.getSalt(), result.getSecretKey(), roles, result.getProfile()))
                                .flatMap(newResult -> {
                                    String token = tokenManager.issueToken(userId);
                                    SignupResponse signupResponse = new SignupResponse(userId, token);
                                    log.info("Users update in BD");
                                    return Mono.just(signupResponse);
                                });
                    } else {
                        log.error("Ошибка создания роли пользователя");
                        return Mono.error(new AlreadyExistsException("Error update user roles"));
                    }
                });
    }

    public Mono<SignupResponse> updateUserProfile(SignupRequest request) {

        String userId = request.getUserId();
        UserProfile profile = request.getProfile();
        return repository.findByUserId(userId)
                .flatMap(result -> {
                    if (result.getUserId() != null) {
                        return repository.save(new User(userId, result.getEmail(), result.getHash(), result.getSalt(), result.getSecretKey(), result.getRoles(), profile))
                                .flatMap(newResult -> {
                                    String token = tokenManager.issueToken(userId);
                                    SignupResponse signupResponse = new SignupResponse(userId, token);
                                    log.info("Users update in BD");
                                    return Mono.just(signupResponse);
                                });
                    } else {
                        log.error("Ошибка обновления профиля пользователя");
                        return Mono.error(new AlreadyExistsException("Error update user"));
                    }
                });
    }
}
