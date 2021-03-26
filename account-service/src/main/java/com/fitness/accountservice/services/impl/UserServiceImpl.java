package com.fitness.accountservice.services.impl;

import com.fitness.accountservice.errors.AlreadyExistsException;
import com.fitness.accountservice.managers.TokenManager;
import com.fitness.accountservice.managers.TotpManager;
import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.MailSender;
import com.fitness.accountservice.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Component("UserService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @Autowired
    private MailSender mailSender;
    private TokenManager tokenManager;
    private UserRepository repository;
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public Mono<SignupResponse> updateUserRole(SignupRequest request) {

        String userId = request.getUserId();
        List<Role> roles = request.getRoles();
        Mono<SignupResponse> response = repository.findByUserId(userId)
                .flatMap(result -> {
                    if (result.getUserId() != null) {
                        return repository.save(new User(userId, result.getEmail(), result.getHash(), result.getSalt(), result.getSecretKey(), roles))
                                .flatMap(result2 -> {
                                    String token = tokenManager.issueToken(userId);
                                    SignupResponse signupResponse = new SignupResponse(userId, token);
                                    log.info("Users update in BD");
                                    return Mono.just(signupResponse);
                                });
                    } else {
                        log.error("Ошибка создания пользователя");
                        return Mono.error(new AlreadyExistsException("Error update user"));

                    }
                });
        return response;
    }
}
