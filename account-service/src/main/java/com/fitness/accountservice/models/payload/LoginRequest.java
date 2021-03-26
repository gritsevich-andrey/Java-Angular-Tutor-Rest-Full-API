package com.fitness.accountservice.models.payload;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
public class LoginRequest {
    @Email(message = "It should have email format")
    @NotBlank(message = "User email is required")
    @Valid
    private String email;
    @NotEmpty(message = "Please, enter password")
    private String password;
}
