package com.fitness.accountservice.errors;

public class AlreadyExistsException extends RuntimeException {
    public AlreadyExistsException(String errorUser) {
        super(errorUser);
    }
}
