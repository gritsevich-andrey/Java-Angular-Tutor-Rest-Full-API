package com.fitness.accountservice.models.payload;

import lombok.Data;

@Data
public class LoginResponse {

//    private boolean success;
    private String userId;
    private String token;
}
