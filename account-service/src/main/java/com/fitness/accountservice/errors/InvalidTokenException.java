package com.fitness.accountservice.errors;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException() {
        super("Not token key");
    }
}
