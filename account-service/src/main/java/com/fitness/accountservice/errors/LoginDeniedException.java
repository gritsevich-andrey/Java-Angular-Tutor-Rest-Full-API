package com.fitness.accountservice.errors;

import org.springframework.security.access.AccessDeniedException;

public class LoginDeniedException extends AccessDeniedException {
    public LoginDeniedException(String msg) {
        super("Error authorized");
    }
}
