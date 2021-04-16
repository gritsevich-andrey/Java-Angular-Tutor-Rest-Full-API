package com.fitness.accountservice.errors;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String error_user) {
        super(error_user);
    }
}
