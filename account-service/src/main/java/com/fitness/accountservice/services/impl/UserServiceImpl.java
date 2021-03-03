package com.fitness.accountservice.services.impl;

import com.fitness.accountservice.errors.AlreadyExistsException;
import com.fitness.accountservice.managers.TokenManager;
import com.fitness.accountservice.managers.TotpManager;
import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.UserService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Component("UserService")
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private TotpManager totpManager;
    private TokenManager tokenManager;
    private UserRepository repository;
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());
    @Override
    public Mono<SignupResponse> updateUser(SignupRequest request) {
        String email = request.getEmail();
        List<Role> roles = request.getRoles();
        String password = request.getPassword();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        String secret = totpManager.generateSecret();
//        User user = new User(null, email,  hash, salt, secret, roles);
        Mono<SignupResponse> response = repository.findByEmail(email)
                .flatMap(result -> {
                    if (result.getUserId() != null) {
                        return repository.save(new User(null, email,  hash, salt, secret, roles))
                                .flatMap(result2 -> {
                            String userId = result2.getUserId();
                            String token = tokenManager.issueToken(userId);
                            SignupResponse signupResponse = new SignupResponse(userId, token, secret,roles);
                            log.info("Users update BD");
                            return Mono.just(signupResponse);
                        });
                    } else {
                        return Mono.error(new AlreadyExistsException());
                    }
                });
        return response;
    }
}
