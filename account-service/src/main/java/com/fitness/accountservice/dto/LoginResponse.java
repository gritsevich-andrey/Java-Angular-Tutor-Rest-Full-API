package com.fitness.accountservice.dto;

import com.fitness.accountservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class LoginResponse {
    private String userId;
    private String token;
    private List<Role> roles;
}
