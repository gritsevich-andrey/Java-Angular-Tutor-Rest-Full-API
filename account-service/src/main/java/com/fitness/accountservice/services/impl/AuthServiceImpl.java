package com.fitness.accountservice.services.impl;

import com.fitness.accountservice.errors.AlreadyExistsException;
import com.fitness.accountservice.errors.LoginDeniedException;
import com.fitness.accountservice.managers.TokenManager;
import com.fitness.accountservice.managers.TotpManager;
import com.fitness.accountservice.models.Role;
import com.fitness.accountservice.models.User;
import com.fitness.accountservice.models.payload.LoginRequest;
import com.fitness.accountservice.models.payload.LoginResponse;
import com.fitness.accountservice.models.payload.SignupRequest;
import com.fitness.accountservice.models.payload.SignupResponse;
import com.fitness.accountservice.repositories.UserRepository;
import com.fitness.accountservice.services.AuthService;
import lombok.AllArgsConstructor;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Component("AuthService")
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {
    private static final Logger log = Logger.getLogger(MethodHandles.lookup().lookupClass());
    private final TokenManager tokenManager;
    private final TotpManager totpManager;
    private final UserRepository repository;

    @Override
    public Mono<SignupResponse> signup(SignupRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        String lessonId = request.getLessonId();
        List<Role> roles = request.getRoles();
        String password = request.getPassword();
        String salt = BCrypt.gensalt();
        String hash = BCrypt.hashpw(password, salt);
        String secret = totpManager.generateSecret();
        User user = new User(null, email, lessonId,  hash, salt, secret, roles);

        Mono<SignupResponse> response = repository.findByEmail(email)
                .defaultIfEmpty(user)
                .flatMap(result -> {
                    if (result.getUserId() == null) {
                        return repository.save(result).flatMap(result2 -> {
                            String userId = result2.getUserId();
                            String token = tokenManager.issueToken(userId);
                            SignupResponse signupResponse = new SignupResponse(userId, token);
                            log.info("Users save in BD");
                            return Mono.just(signupResponse);
                        });
                    } else {
                        return Mono.error(new AlreadyExistsException("Error signup"));
                    }
                });
        return response;
    }

    @Override
    public Mono<LoginResponse> login(LoginRequest request) {

        String email = request.getEmail().trim().toLowerCase();
        String password = request.getPassword();
        Mono<LoginResponse> response = repository.findByEmail(email)
                .defaultIfEmpty(new User())
                .flatMap(user -> {
                    if (user.getUserId() == null) {
                        return Mono.empty();
                    } else {
                        String salt = user.getSalt();
                        String secret = user.getSecretKey();
                        boolean passwordMatch = BCrypt.hashpw(password, salt).equalsIgnoreCase(user.getHash());
                        if (passwordMatch) {
                            boolean codeMatched = totpManager.validateCode(secret);
                            if (codeMatched) {
                                String token = tokenManager.issueToken(user.getUserId());
                                LoginResponse loginResponse = new LoginResponse();
                                loginResponse.setToken(token);
                                loginResponse.setUserId(user.getUserId());
                                loginResponse.setRoles(user.getRoles());
                                return Mono.just(loginResponse);
                            } else {
                                return Mono.error(new LoginDeniedException("Error authorization"));
                            }
                        } else {
                            return Mono.error(new LoginDeniedException("Error authorization"));
                        }
                    }
                });
        return response;
    }

    @Override
    public Mono<String> parseToken(String token) {
        return tokenManager.parse(token);
    }
}
