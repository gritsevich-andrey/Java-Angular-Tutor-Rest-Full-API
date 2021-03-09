package com.fitness.accountservice.models.payload;

import com.fitness.accountservice.models.Role;
import lombok.Data;

import java.util.List;

@Data
public class SignupRequest {
    private String userId;
    private String email;
    private String password;
    private String lessonId;
    private List<Role> roles;

}
