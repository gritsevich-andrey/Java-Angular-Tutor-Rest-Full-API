package com.fitness.accountservice.models.payload;

import lombok.Data;

@Data
public class SignupRequest {

    private String email;
    private String password;

}
