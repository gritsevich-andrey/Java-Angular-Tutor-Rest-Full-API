package com.fitness.accountservice.models;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER, ADMIN, INSTRUCTOR, PUPIL;

    @Override
    public String getAuthority() {
        return name();
    }
}
